package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

sealed interface SignUpEvent {
    data object NavigateToSignIn : SignUpEvent
    data object SignUpSuccess : SignUpEvent
    data class SignUpFailure(val message: String) : SignUpEvent
}
