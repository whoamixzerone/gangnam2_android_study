package com.survivalcoding.gangnam2kiandroidstudy.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.survivalcoding.gangnam2kiandroidstudy.data.model.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("select * from user where id = :id")
    fun loadById(id: Int): Flow<User?>

    @Insert
    suspend fun save(user: User)

    @Update
    suspend fun update(user: User)
}
