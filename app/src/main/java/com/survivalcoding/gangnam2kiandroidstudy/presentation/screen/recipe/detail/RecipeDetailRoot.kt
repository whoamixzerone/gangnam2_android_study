package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun RecipeDetailRoot(
    recipeId: Int,
    modifier: Modifier = Modifier,
    viewModel: RecipeDetailViewModel = hiltViewModel(
        creationCallback = { factory: RecipeDetailViewModel.Factory ->
            factory.create(recipeId)
        }
    )
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    RecipeDetailScreen(
        uiState = uiState.value,
        modifier = modifier,
        onUpdateTabInfo = viewModel::updateTabInfo
    )
}
