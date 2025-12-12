package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

data class SavedRecipeState(
    val data: List<Recipe> = emptyList(),
    val error: String? = null,
)