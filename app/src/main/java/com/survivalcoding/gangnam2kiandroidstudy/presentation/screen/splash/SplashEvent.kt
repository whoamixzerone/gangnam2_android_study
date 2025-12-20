package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

sealed interface SplashEvent {
    data object OnNavigateToSignIn : SplashEvent
    data class ShowSnackbar(val message: String) : SplashEvent
}