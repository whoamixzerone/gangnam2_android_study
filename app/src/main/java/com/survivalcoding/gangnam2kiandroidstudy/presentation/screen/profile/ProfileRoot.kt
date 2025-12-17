package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProfileRoot(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    ProfileScreen(
        uiState = uiState.value
    )
}