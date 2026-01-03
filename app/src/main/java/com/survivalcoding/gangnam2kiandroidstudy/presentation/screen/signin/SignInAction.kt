package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signin

sealed interface SignInAction {
    data object OnSignInClick : SignInAction
    data object OnSignUpClick : SignInAction
    data object OnGoogleSignInClick : SignInAction
    data object OnFaceBookSignInClick : SignInAction

    data class OnEmailValueChange(val email: String) : SignInAction
    data class OnPasswordValueChange(val password: String) : SignInAction
}
