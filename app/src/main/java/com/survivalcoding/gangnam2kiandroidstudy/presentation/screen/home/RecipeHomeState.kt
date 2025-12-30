package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class RecipeHomeState(
    val recipes: ImmutableList<Recipe> = persistentListOf(),
    val savedRecipeIds: Set<Int> = emptySet(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val query: String = "",
    val selectedCategory: RecipeCategory = RecipeCategory.ALL,
    val isSearchEnabled: Boolean = false
)
