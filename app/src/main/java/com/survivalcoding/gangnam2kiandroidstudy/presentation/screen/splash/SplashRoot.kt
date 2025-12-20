package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashRoot(
    onNavigateToSignIn: () -> Unit = {},
    viewModel: SplashViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.uiEvent) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                SplashEvent.OnNavigateToSignIn -> onNavigateToSignIn()
                is SplashEvent.ShowSnackbar -> snackbarHostState.showSnackbar(
                    message = event.message,
                    duration = SnackbarDuration.Long
                )
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        SplashScreen(
            uiState = uiState,
            onAction = viewModel::onAction
        )
    }
}
