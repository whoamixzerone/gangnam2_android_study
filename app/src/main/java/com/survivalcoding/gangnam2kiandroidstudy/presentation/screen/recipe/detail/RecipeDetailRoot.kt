package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RecipeDetailRoot(
    recipeId: Int,
    modifier: Modifier = Modifier,
    viewModel: RecipeDetailViewModel = koinViewModel { parametersOf(recipeId) }
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    RecipeDetailScreen(
        uiState = uiState.value,
        modifier = modifier,
        onUpdateTabInfo = viewModel::updateTabInfo
    )
}
