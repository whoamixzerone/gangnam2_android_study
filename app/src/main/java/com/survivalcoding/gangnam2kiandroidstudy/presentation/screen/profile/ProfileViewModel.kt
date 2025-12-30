package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile

import androidx.lifecycle.ViewModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileState())
    val uiState= _uiState.asStateFlow()

}