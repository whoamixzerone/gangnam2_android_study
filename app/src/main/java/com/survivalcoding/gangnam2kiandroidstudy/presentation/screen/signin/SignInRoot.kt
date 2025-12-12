package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signin

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignInRoot(
    modifier: Modifier = Modifier,
    navigateSignIn: () -> Unit = {},
    navigateSignUp: () -> Unit = {},
    viewModel: SignInViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    SignInScreen(
        uiState = uiState.value,
        modifier = modifier,
        onClickSignIn = navigateSignIn,
        onClickSignUp = navigateSignUp,
    )
}