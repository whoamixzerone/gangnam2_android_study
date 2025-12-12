package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignUpRoot(
    modifier: Modifier = Modifier,
    navigateSignIn: () -> Unit = {},
    viewModel: SignUpViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    SignUpScreen(
        uiState = uiState.value,
        modifier = modifier,
        onClickSignIn = navigateSignIn,
    )
}