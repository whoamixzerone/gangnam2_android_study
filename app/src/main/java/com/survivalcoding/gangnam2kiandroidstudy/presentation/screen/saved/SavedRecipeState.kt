package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved

import androidx.compose.runtime.Immutable
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

@Immutable
data class SavedRecipeState(
    val data: List<Recipe> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)