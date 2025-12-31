package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.runtime.Immutable
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class RecipeHomeState(
    val recipes: ImmutableList<Recipe> = persistentListOf(),
    val savedRecipeIds: List<Int> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val query: String = "",
    val selectedCategory: RecipeCategory = RecipeCategory.ALL,
    val isSearchEnabled: Boolean = false
)
