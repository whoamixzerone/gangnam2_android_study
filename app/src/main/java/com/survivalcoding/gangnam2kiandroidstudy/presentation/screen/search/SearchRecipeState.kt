package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search.filter.FilterSearchState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class SearchRecipeState(
    val recipes: ImmutableList<Recipe> = persistentListOf(),
    val filterRecipes: ImmutableList<Recipe> = persistentListOf(),
    val query: String = "",
    val showBottomSheet: Boolean = false,
    val isLoading: Boolean = true,
    val error: String? = null,
    val isSearchEnabled: Boolean = true,
    val filterSearchState: FilterSearchState = FilterSearchState()
)
