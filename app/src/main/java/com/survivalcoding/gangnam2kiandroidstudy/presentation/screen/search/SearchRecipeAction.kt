package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search.filter.FilterSearchState

sealed interface SearchRecipeAction {

    data class OnRecipeClick(val recipeId: Int) : SearchRecipeAction

    data object OnSearchDone : SearchRecipeAction
    data class UpdateQuery(val query: String) : SearchRecipeAction

    data object OnFilterSettingClick : SearchRecipeAction
    data class ApplyFilter(val filter: FilterSearchState) : SearchRecipeAction
    data object CancelFilter : SearchRecipeAction
}