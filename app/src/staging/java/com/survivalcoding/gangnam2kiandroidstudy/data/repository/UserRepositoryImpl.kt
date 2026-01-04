package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.dao.UserDao
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.User
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.UserRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.model.entity.User as UserEntity
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userDao: UserDao,
) : UserRepository {

    override fun loadById(id: Int): Flow<User?> {
        return userDao.loadById(id).map { entity ->
            entity?.let {
                User(
                    id = it.id,
                    name = "",
                    image = "",
                    address = "",
                    work = "",
                    introduce = "",
                    bookmarks = it.recipeIds?.toPersistentList() ?: persistentListOf()
                )
            }
        }
    }

    override suspend fun save(user: User) {
        withContext(Dispatchers.IO) {
            userDao.save(
                UserEntity(
                    id = user.id,
                    recipeIds = user.bookmarks
                )
            )
        }
    }

    override suspend fun updateSavedRecipe(id: Int, recipeId: Int) {
        withContext(Dispatchers.IO) {
            val user = loadById(id).firstOrNull()

            user?.let {
                val bookmarks = it.bookmarks.toMutableList()
                if (bookmarks.contains(recipeId)) {
                    bookmarks.remove(recipeId)
                } else {
                    bookmarks.add(recipeId)
                }

                userDao.update(
                    UserEntity(
                        id = it.id,
                        recipeIds = bookmarks
                    )
                )
            }
        }
    }
}