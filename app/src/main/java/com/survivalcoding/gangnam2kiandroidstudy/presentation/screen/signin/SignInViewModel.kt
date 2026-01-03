package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SignInEvent>()
    val event = _event.asSharedFlow()

    fun handleAction(action: SignInAction) {
        when (action) {
            SignInAction.OnSignInClick -> handleSignIn()
            SignInAction.OnSignUpClick -> emitEvent(SignInEvent.NavigateToSignUp)
            SignInAction.OnGoogleSignInClick -> handleSignInWithGoogle()
            SignInAction.OnFaceBookSignInClick -> TODO()
            is SignInAction.OnEmailValueChange -> handleEmailValueChange(action.email)
            is SignInAction.OnPasswordValueChange -> handlePasswordValueChange(action.password)
        }
    }

    private fun handleSignIn() {
        authRepository.signIn(
            email = _state.value.email,
            password = _state.value.password
        )
            .onEach { result ->
                when (result) {
                    is Result.Success -> emitEvent(SignInEvent.SignInSuccess)
                    is Result.Failure -> emitEvent(SignInEvent.SignInFailure(result.error))
                }
            }
            .launchIn(viewModelScope)
    }

    private fun handleSignInWithGoogle() {
        authRepository.signInWithGoogle()
            .onEach { result ->
                when (result) {
                    is Result.Success -> emitEvent(SignInEvent.SignInSuccess)
                    is Result.Failure -> {
                        val isNoAccount = result.error.contains("Google 계정이 없습니다")
                        emitEvent(SignInEvent.SignInFailure(result.error, isNoAccount))
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun handleEmailValueChange(email: String) {
        _state.update {
            it.copy(email = email)
        }
    }

    private fun handlePasswordValueChange(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    private fun emitEvent(event: SignInEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}