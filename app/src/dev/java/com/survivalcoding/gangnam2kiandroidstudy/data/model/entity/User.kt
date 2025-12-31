package com.survivalcoding.gangnam2kiandroidstudy.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val recipeIds: List<Int>?
)
