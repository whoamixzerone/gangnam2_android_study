package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signUp(email: String, password: String): Flow<Result<Unit, String>>
    fun signIn(email: String, password: String): Flow<Result<Unit, String>>
    fun signInWithGoogle(): Flow<Result<Unit, String>>
}