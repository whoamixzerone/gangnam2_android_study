package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

sealed interface SearchRecipeEvent {

    data class NavigateToDetail(val recipeId: Int): SearchRecipeEvent
    data class ShowSnackbar(val message: String) : SearchRecipeEvent
}