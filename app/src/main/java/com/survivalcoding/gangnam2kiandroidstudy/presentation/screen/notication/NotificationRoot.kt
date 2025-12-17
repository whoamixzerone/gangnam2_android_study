package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.notication

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun NotificationRoot(
    modifier: Modifier = Modifier,
    viewModel: NotificationViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    NotificationScreen(
        uiState = uiState.value
    )
}