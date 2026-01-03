package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signin

sealed interface SignInEvent {
    data object NavigateToSignUp : SignInEvent
    data object SignInSuccess : SignInEvent

    data class SignInFailure(val message: String, val isNoCredential: Boolean = false) : SignInEvent
}
