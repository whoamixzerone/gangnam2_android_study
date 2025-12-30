package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SavedRecipeRoot(
    modifier: Modifier = Modifier,
    onNavigateToDetail: (recipeId: Int) -> Unit = {},
    viewModel: SavedRecipeViewModel = koinViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberLazyListState()

    val isAtBottom by remember {
        derivedStateOf {
            val layoutInfo = scrollState.layoutInfo
            val totalItemsCount = layoutInfo.totalItemsCount

            !scrollState.canScrollForward && totalItemsCount > 0
        }
    }

    LaunchedEffect(isAtBottom) {
        if (isAtBottom) {
            snackbarHostState.showSnackbar(
                message = "마지막 항목에 도달했습니다.",
                duration = SnackbarDuration.Short
            )
        }
    }

    LaunchedEffect(viewModel.uiEvent) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is SavedRecipeEvent.NavigateToDetail -> onNavigateToDetail(event.recipeId)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        SavedRecipeScreen(
            uiState = uiState.value,
            scrollState = scrollState,
            modifier = modifier,
            onAction = viewModel::onAction
        )
    }
}
