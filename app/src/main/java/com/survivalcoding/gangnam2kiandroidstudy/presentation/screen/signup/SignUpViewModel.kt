package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

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

class SignUpViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SignUpEvent>()
    val event = _event.asSharedFlow()

    fun handleAction(action: SignUpAction) {
        when (action) {
            SignUpAction.OnCheckBoxClick -> handleCheckBoxChange()
            SignUpAction.OnSignInClick -> emitEvent(SignUpEvent.NavigateToSignIn)
            SignUpAction.OnSignUpClick -> handleSignUp()
            is SignUpAction.OnNameValueChange -> handleNameValueChange(action.name)
            is SignUpAction.OnEmailValueChange -> handleEmailValueChange(action.email)
            is SignUpAction.OnPasswordValueChange -> handlePasswordValueChange(action.password)
            is SignUpAction.OnConfirmPasswordValueChange -> handleConfirmPasswordValueChange(action.confirmPassword)
        }
    }

    private fun handleCheckBoxChange() {
        val checked = _state.value.isChecked

        _state.update {
            it.copy(isChecked = !checked)
        }
    }

    private fun handleSignUp() {
        val email = _state.value.email
        val password = _state.value.password
        val confirmPassword = _state.value.confirmPassword
        val checked = _state.value.isChecked

        if (password != confirmPassword) {
            return
        }
        if (!checked) {
            return
        }

        authRepository.signUp(email = email, password = password)
            .onEach { result ->
                when (result) {
                    is Result.Success -> emitEvent(SignUpEvent.SignUpSuccess)
                    is Result.Failure -> emitEvent(SignUpEvent.SignUpFailure(result.error))
                }
            }.launchIn(viewModelScope)
    }

    private fun handleNameValueChange(name: String) {
        _state.update {
            it.copy(name = name)
        }
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

    private fun handleConfirmPasswordValueChange(confirmPassword: String) {
        _state.update {
            it.copy(confirmPassword = confirmPassword)
        }
    }

    private fun emitEvent(event: SignUpEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}