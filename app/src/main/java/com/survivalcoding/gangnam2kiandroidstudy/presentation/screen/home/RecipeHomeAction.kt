package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory

sealed interface RecipeHomeAction {

    data object OnSearchClick : RecipeHomeAction
    data class OnRecipeClick(val recipeId: Int) : RecipeHomeAction

    data class SelectedCategory(val selectedCategory: RecipeCategory) : RecipeHomeAction
    data class ToggleBookmark(val recipeId: Int) : RecipeHomeAction
}
