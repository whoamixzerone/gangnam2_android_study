package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.User
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.UserRepository
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class RecipeHomeViewModel(
    private val recipeRepository: RecipeRepository,
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(RecipeHomeState())
    val state = _state.asStateFlow()

    private val _uiEvent = MutableSharedFlow<RecipeHomeEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val _searchQuery = _state.map { it.query }
        .debounce(1000)

    init {
        fetchSearchRecipes(_state.value.query)

        viewModelScope.launch {
            _searchQuery.drop(1)
                .distinctUntilChanged()
                .collect { query ->
                    fetchSearchRecipes(query)
                }
        }

        viewModelScope.launch {
            userRepository.loadById(1)
                .collect { user ->
                    _state.update {
                        it.copy(savedRecipeIds = user?.bookmarks ?: emptyList())
                    }
                }
        }
    }

    fun onAction(action: RecipeHomeAction) {
        when (action) {
            RecipeHomeAction.OnSearchClick -> {
                viewModelScope.launch {
                    _uiEvent.emit(RecipeHomeEvent.NavigateToSearchRecipe)
                }
            }

            is RecipeHomeAction.OnRecipeClick -> {
                viewModelScope.launch {
                    _uiEvent.emit(RecipeHomeEvent.NavigateToDetail(action.recipeId))
                }
            }

            is RecipeHomeAction.SelectedCategory -> updateSelectedCategory(action.selectedCategory)
            is RecipeHomeAction.ToggleBookmark -> toggleBookmark(action.recipeId)
        }
    }

    private fun fetchSearchRecipes(query: String) {
        try {
            viewModelScope.launch {
                val response = recipeRepository.search(
                    query = query,
                    time = "All",
                    rate = 1.0,
                    category = _state.value.selectedCategory.displayName
                )

                when (response) {
                    is Result.Success -> _state.update {
                        it.copy(recipes = response.data.toPersistentList(), isLoading = false)
                    }

                    is Result.Failure -> {
                        _state.update {
                            it.copy(isLoading = false, error = response.error.toString())
                        }
                    }
                }
            }
        } catch (e: Exception) {
            _state.update {
                it.copy(isLoading = false, error = "Error fetching ${e.message}")
            }
        }
    }

    private fun updateSelectedCategory(selectedCategory: RecipeCategory) {
        _state.update {
            it.copy(selectedCategory = selectedCategory)
        }

        fetchSearchRecipes(_state.value.query)
    }

    private fun toggleBookmark(recipeId: Int) {
        viewModelScope.launch {
            // TODO : User가 없기 때문에 임시 로직
            val user = userRepository.loadById(1).firstOrNull()

            if (user == null) {
                userRepository.save(
                    User(
                        id = 0,
                        name = "",
                        image = "",
                        address = "",
                        work = "",
                        introduce = "",
                        bookmarks = persistentListOf()
                    )
                )
            }

            userRepository.updateSavedRecipe(1, recipeId)
        }
    }

}
