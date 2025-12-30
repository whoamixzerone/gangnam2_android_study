package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.CopyLinkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeDetailViewModel(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val copyLinkUseCase: CopyLinkUseCase,
    val recipeId: Int,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeDetailState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<RecipeDetailEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        fetchRecipes()
    }

    fun onAction(action: RecipeDetailAction) {
        when (action) {
            is RecipeDetailAction.OnFollowClick -> TODO()
            is RecipeDetailAction.OnDropdownMenuClick -> updateExpanded(action.expanded)
            is RecipeDetailAction.UpdateTabInfo -> updateTabInfo(action.index)
            RecipeDetailAction.OnArrowBackClick -> emitEvent(RecipeDetailEvent.NavigateToBack)
            RecipeDetailAction.OnShareClick -> updateVisibleShare()
            RecipeDetailAction.OnLinkDialogCloseClick -> updateVisibleShare()
            is RecipeDetailAction.OnCopyLinkClick -> executeClipboard(action.link)
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

    private fun updateTabInfo(index: Int) {
        _uiState.update {
            it.copy(selectedTabIndex = index)
        }
    }

    private fun updateExpanded(expanded: Boolean) {
        _uiState.update {
            it.copy(expandedMenu = expanded)
        }
    }

    private fun updateVisibleShare() {
        _uiState.update {
            it.copy(visibleShare = !it.visibleShare)
        }
    }

    private fun executeClipboard(link: String) {
        copyLinkUseCase(link)

        emitEvent(RecipeDetailEvent.CopyToClipboard)
    }

    private fun emitEvent(event: RecipeDetailEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }

}
