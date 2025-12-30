package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved

sealed interface SavedRecipeAction {
    data class OnUnBookmark(val recipeId: Int) : SavedRecipeAction
    data class OnRecipeItemClick(val recipeId: Int) : SavedRecipeAction
}