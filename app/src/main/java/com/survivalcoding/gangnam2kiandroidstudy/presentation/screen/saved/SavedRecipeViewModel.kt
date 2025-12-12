package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedRecipeViewModel(private val repository: RecipeRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(SavedRecipeState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchRecipes()
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            val result = repository.findAll()

            when (result) {
                is Result.Success -> _uiState.update {
                    it.copy(data = result.data)
                }

                is Result.Failure -> {
                    _uiState.update {
                        it.copy(error = result.error.toString())
                    }
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val recipeRepository = (this[APPLICATION_KEY] as AppApplication).recipeRepository
                SavedRecipeViewModel(recipeRepository)
            }
        }

        fun factory(application: AppApplication) = viewModelFactory {
            initializer {
                SavedRecipeViewModel(application.recipeRepository)
            }
        }
    }
}