package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.dao.UserDao
import com.survivalcoding.gangnam2kiandroidstudy.data.model.entity.User
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userDao: UserDao,
) : UserRepository {

    override suspend fun loadById(id: Int): Flow<User?> {
        return withContext(Dispatchers.IO) {
            userDao.loadById(id)
        }
    }

    override suspend fun save(user: User) {
        withContext(Dispatchers.IO) {
            userDao.save(user)
        }
    }

    override suspend fun updateSavedRecipe(id: Int, recipeId: Int) {
        withContext(Dispatchers.IO) {
            val user = loadById(id).firstOrNull()

            user?.let {
                val recipeIds = it.recipeIds?.toMutableList() ?: mutableListOf()
                if (recipeIds.contains(recipeId)) {
                    recipeIds.remove(recipeId)
                } else {
                    recipeIds.add(recipeId)
                }

                userDao.update(it.copy(recipeIds = recipeIds))
            }
        }
    }
}
