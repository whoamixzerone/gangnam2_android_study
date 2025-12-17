package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignUpRoot(
    modifier: Modifier = Modifier,
    navigateSignIn: () -> Unit = {},
    viewModel: SignUpViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    SignUpScreen(
        uiState = uiState.value,
        modifier = modifier,
        onClickSignIn = navigateSignIn,
    )
}