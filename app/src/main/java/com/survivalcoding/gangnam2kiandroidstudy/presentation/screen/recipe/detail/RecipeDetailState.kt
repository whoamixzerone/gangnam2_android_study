package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail

import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class RecipeDetailState(
    val recipe: Recipe? = null,
    val shareLink: String = "app.Recipe.co/jollof_rice",
    val selectedTabIndex: Int = 0,
    val tabLabels: ImmutableList<Int> = persistentListOf(R.string.tab_ingredient, R.string.tab_procedure),
    val expandedMenu: Boolean = false,
    val visibleShare: Boolean = false,
    val error: String? = null
)
