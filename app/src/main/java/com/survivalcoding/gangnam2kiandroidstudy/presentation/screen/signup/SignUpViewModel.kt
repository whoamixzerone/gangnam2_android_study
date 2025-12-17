package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class SignUpViewModel @Inject constructor(
    // TODO UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpState())
    val uiState = _uiState.asStateFlow()

}