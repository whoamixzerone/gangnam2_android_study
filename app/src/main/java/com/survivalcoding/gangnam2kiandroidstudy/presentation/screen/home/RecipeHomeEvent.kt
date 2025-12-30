package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

sealed interface RecipeHomeEvent {

    data object NavigateToSearchRecipe : RecipeHomeEvent

    data class NavigateToDetail(val recipeId: Int) : RecipeHomeEvent
}