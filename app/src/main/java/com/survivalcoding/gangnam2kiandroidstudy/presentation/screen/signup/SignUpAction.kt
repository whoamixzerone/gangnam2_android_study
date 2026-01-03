package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

sealed interface SignUpAction {
    data object OnCheckBoxClick : SignUpAction
    data object OnSignInClick : SignUpAction
    data object OnSignUpClick : SignUpAction

    data class OnNameValueChange(val name: String) : SignUpAction
    data class OnEmailValueChange(val email: String) : SignUpAction
    data class OnPasswordValueChange(val password: String) : SignUpAction
    data class OnConfirmPasswordValueChange(val confirmPassword: String) : SignUpAction
}
