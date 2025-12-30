package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail

sealed interface RecipeDetailEvent {
    data object NavigateToBack : RecipeDetailEvent
    data object CopyToClipboard : RecipeDetailEvent
}
