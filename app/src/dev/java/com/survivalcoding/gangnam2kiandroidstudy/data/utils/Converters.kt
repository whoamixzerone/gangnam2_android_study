package com.survivalcoding.gangnam2kiandroidstudy.data.utils

import androidx.room.TypeConverter
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromIntList(value: List<Int>?): String? {
        return value?.let {
            Json.encodeToString(ListSerializer(Int.serializer()), it)
        }
    }

    @TypeConverter
    fun toIntList(value: String?): List<Int>? {
        return value?.let {
            Json.decodeFromString(ListSerializer(Int.serializer()), it)
        }
    }
}
