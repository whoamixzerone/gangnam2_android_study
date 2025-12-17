package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.notication

import androidx.lifecycle.ViewModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(NotificationState())
    val uiState = _uiState.asStateFlow()


}