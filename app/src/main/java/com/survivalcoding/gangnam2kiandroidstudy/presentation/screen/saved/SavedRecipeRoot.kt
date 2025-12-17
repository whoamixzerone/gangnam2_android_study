package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SavedRecipeRoot(
    modifier: Modifier = Modifier,
    navigateToDetail: (recipeId: Int) -> Unit = {},
    viewModel: SavedRecipeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    SavedRecipeScreen(
        uiState = uiState.value,
        modifier = modifier,
        onUnBookmark = viewModel::unBookmark,
        navigateToDetail = navigateToDetail
    )
}