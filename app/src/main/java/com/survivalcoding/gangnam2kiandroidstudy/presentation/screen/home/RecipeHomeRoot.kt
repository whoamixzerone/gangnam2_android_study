package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RecipeHomeRoot(
    onNavigateToSearchRecipe: () -> Unit = {},
    onNavigateToDetail: (recipeId: Int) -> Unit = {},
    viewModel: RecipeHomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                RecipeHomeEvent.NavigateToSearchRecipe -> onNavigateToSearchRecipe()
                is RecipeHomeEvent.NavigateToDetail -> onNavigateToDetail(event.recipeId)
            }
        }
    }

    RecipeHomeScreen(
        state = state,
        onAction = viewModel::onAction
    )
}
