package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileState())
    val uiState= _uiState.asStateFlow()

    companion object {
        fun factory(application: AppApplication) = viewModelFactory {
            initializer {
                ProfileViewModel(application.recipeRepository)
            }
        }
    }
}