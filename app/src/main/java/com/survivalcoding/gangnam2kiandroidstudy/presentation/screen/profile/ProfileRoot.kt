package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication

@Composable
fun ProfileRoot(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModel.factory(LocalContext.current.applicationContext as AppApplication)
    )
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    ProfileScreen(
        uiState = uiState.value
    )
}