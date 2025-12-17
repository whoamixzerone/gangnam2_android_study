package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signin

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class SignInViewModel @Inject constructor(
    // TODO UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignInState())
    val uiState = _uiState.asStateFlow()

}