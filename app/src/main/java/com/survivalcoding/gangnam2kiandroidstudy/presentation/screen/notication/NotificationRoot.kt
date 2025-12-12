package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.notication

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication

@Composable
fun NotificationRoot(
    modifier: Modifier = Modifier,
    viewModel: NotificationViewModel = viewModel(
        factory = NotificationViewModel.factory(LocalContext.current.applicationContext as AppApplication)
    )
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    NotificationScreen(
        uiState = uiState.value
    )
}