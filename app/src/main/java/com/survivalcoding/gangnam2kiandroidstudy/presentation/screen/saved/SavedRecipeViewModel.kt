package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipesUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedRecipeViewModel(private val getRecipesUseCase: GetRecipesUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(SavedRecipeState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<SavedRecipeEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        fetchRecipes()
    }

    fun onAction(action: SavedRecipeAction) {
        when (action) {
            is SavedRecipeAction.OnUnBookmark -> unBookmark(action.recipeId)
            is SavedRecipeAction.OnRecipeItemClick -> emitEvent(SavedRecipeEvent.NavigateToDetail(action.recipeId))
        }
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            val result = getRecipesUseCase.invoke()

            when (result) {
                is Result.Success -> _uiState.update {
                    it.copy(
                        data = result.data,
                        isLoading = false
                    )
                }

                is Result.Failure -> {
                    _uiState.update {
                        it.copy(
                            error = result.error.toString(),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun unBookmark(id: Int) {
        val recipes = _uiState.value.data.filter() {
            it.id != id
        }

        _uiState.update {
            it.copy(data = recipes)
        }

//        TODO 나중에 Repository에 Bookmark 삭제하는 기능 구현
    }

    private fun emitEvent(event: SavedRecipeEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }

}