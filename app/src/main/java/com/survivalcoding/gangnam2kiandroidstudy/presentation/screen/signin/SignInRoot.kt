package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signin

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignInRoot(
    modifier: Modifier = Modifier,
    navigateSignIn: () -> Unit = {},
    navigateSignUp: () -> Unit = {},
    viewModel: SignInViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    SignInScreen(
        uiState = uiState.value,
        modifier = modifier,
        onClickSignIn = navigateSignIn,
        onClickSignUp = navigateSignUp,
    )
}