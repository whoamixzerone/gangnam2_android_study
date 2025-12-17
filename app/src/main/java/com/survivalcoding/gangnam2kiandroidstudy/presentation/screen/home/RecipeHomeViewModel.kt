package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class RecipeHomeViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {
    private val _state = MutableStateFlow(RecipeHomeState())
    val state = _state.asStateFlow()

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
    }

    fun updateSelectedCategory(selectedCategory: RecipeCategory) {
        _state.update {
            it.copy(selectedCategory = selectedCategory)
        }

        fetchSearchRecipes(_state.value.query)
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
                        it.copy(recipes = response.data, isLoading = false)
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
}
