package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SignUpRoot(
    modifier: Modifier = Modifier,
    navigateSignIn: () -> Unit = {},
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    SignUpScreen(
        uiState = uiState.value,
        modifier = modifier,
        onClickSignIn = navigateSignIn,
    )
}