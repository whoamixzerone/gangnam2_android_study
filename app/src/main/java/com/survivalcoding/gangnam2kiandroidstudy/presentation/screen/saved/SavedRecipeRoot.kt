package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SavedRecipeRoot(
    modifier: Modifier = Modifier,
    navigateToDetail: (recipeId: Int) -> Unit = {},
    viewModel: SavedRecipeViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    SavedRecipeScreen(
        uiState = uiState.value,
        modifier = modifier,
        onUnBookmark = viewModel::unBookmark,
        navigateToDetail = navigateToDetail
    )
}