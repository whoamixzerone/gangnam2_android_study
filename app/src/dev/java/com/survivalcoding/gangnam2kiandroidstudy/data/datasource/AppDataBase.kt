package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.survivalcoding.gangnam2kiandroidstudy.data.dao.UserDao
import com.survivalcoding.gangnam2kiandroidstudy.data.model.entity.User
import com.survivalcoding.gangnam2kiandroidstudy.data.utils.Converters

@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
