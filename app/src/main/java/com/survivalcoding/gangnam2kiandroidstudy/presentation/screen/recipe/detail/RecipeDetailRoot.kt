package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RecipeDetailRoot(
    recipeId: Int,
    onNavigateToBack: () -> Unit,
    viewModel: RecipeDetailViewModel = koinViewModel { parametersOf(recipeId) },
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(viewModel.uiEvent) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                RecipeDetailEvent.NavigateToBack -> onNavigateToBack()
                RecipeDetailEvent.CopyToClipboard -> {
                    Toast.makeText(context, "Link Copied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    RecipeDetailScreen(
        uiState = uiState.value,
        onAction = viewModel::onAction
    )
}
