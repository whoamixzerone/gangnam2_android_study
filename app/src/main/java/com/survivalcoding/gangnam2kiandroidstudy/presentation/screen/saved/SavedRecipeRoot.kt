package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication

@Composable
fun SavedRecipeRoot(
    modifier: Modifier = Modifier,
    viewModel: SavedRecipeViewModel = viewModel(
        factory = SavedRecipeViewModel.factory(LocalContext.current.applicationContext as AppApplication)
    ),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    SavedRecipeScreen(uiState = uiState.value)
}