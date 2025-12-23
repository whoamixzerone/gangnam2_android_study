package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved

sealed interface SavedRecipeEvent {
    data class NavigateToDetail(val recipeId: Int) : SavedRecipeEvent
}