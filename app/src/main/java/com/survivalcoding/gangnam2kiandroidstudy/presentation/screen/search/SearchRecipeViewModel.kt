package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search.filter.FilterSearchState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchRecipeViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchRecipeState())
    val uiState: StateFlow<SearchRecipeState> = _uiState.asStateFlow()

    private val _searchQuery = _uiState
        .map { it.query }
        .debounce(1000)


    init {
        fetchRecipes()

        viewModelScope.launch {
            _searchQuery
                .drop(1)
                .distinctUntilChanged()
                .collect { query ->
                    fetchSearchRecipes(query)
                }
        }
    }

    fun updateSearch(query: String) {
        _uiState.update {
            it.copy(query = query)
        }
    }

    fun updateFilterSearchState(filterSearchState: FilterSearchState) {
        _uiState.update {
            it.copy(
                filterSearchState = filterSearchState,
                showBottomSheet = false
            )
        }

        fetchSearchRecipes(_uiState.value.query)
    }

    fun performSearch() {
        fetchSearchRecipes(_uiState.value.query)
    }

    fun toggleFilterSetting() {
        _uiState.update {
            it.copy(showBottomSheet = !it.showBottomSheet)
        }
    }

    private fun fetchRecipes() {
        try {
            viewModelScope.launch {
                val response = recipeRepository.findAll()

                when (response) {
                    is Result.Success -> _uiState.update {
                        it.copy(recipes = response.data, isLoading = false)
                    }

                    is Result.Failure -> {
                        _uiState.update {
                            it.copy(isLoading = false, error = response.error.toString())
                        }
                    }
                }
            }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(isLoading = false, error = "Error fetching ${e.message}")
            }
        }
    }

    private fun fetchSearchRecipes(query: String) {
        try {
            viewModelScope.launch {
                val response = recipeRepository.search(
                    query = query,
                    _uiState.value.filterSearchState.time,
                    _uiState.value.filterSearchState.rate,
                    _uiState.value.filterSearchState.category.displayName
                )
                Log.d("SearchRecipeViewModel fetchSearchRecipes", "$response")

                when (response) {
                    is Result.Success -> _uiState.update {
                        it.copy(filterRecipes = response.data, isLoading = false)
                    }

                    is Result.Failure -> {
                        _uiState.update {
                            it.copy(isLoading = false, error = response.error.toString())
                        }
                    }
                }
            }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(isLoading = false, error = "Error fetching ${e.message}")
            }
        }
    }


}
