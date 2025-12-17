package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = RecipeDetailViewModel.Factory::class)
class RecipeDetailViewModel @AssistedInject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    @Assisted private val recipeId: Int
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(recipeId: Int): RecipeDetailViewModel
    }

    private val _uiState = MutableStateFlow(RecipeDetailState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchRecipes()
    }

    fun updateTabInfo(index: Int) {
        Log.d("RecipeDetailViewModel updateTabInfo", "index : $index")
        _uiState.update {
            it.copy(selectedTabIndex = index)
        }
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            try {
                val response = getRecipeDetailsUseCase.invoke(recipeId)

                when (response) {
                    is Result.Success -> {
                        _uiState.update {
                            it.copy(recipe = response.data)
                        }
                    }

                    is Result.Failure -> _uiState.update {
                        it.copy(
                            error = response.error.toString()
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        recipe = null,
                        error = "Fetch recipe ${e.message}"
                    )
                }
            }
        }
    }

}
