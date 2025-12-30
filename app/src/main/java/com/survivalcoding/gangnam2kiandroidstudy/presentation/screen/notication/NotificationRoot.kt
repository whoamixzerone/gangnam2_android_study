package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.notication

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NotificationRoot(
    modifier: Modifier = Modifier,
    viewModel: NotificationViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    NotificationScreen(
        uiState = uiState.value
    )
}