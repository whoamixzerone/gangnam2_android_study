package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun loadById(id: Int): Flow<User?>
    suspend fun save(user: User)
    suspend fun updateSavedRecipe(id: Int, recipeId: Int)
}