package com.survivalcoding.gangnam2kiandroidstudy.data.utils

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromIntList(value: List<Int>?): String? {
        return value?.let {
            Json.encodeToString(it)
        }
    }

    @TypeConverter
    fun toIntList(value: String?): List<Int>? {
        return value?.let {
            Json.decodeFromString(it)
        }
    }
}
