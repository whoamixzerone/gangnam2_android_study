package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile

import androidx.lifecycle.ViewModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileState())
    val uiState= _uiState.asStateFlow()

}