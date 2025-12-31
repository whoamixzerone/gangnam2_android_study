package com.survivalcoding.gangnam2kiandroidstudy.data.dto

import kotlinx.collections.immutable.ImmutableList
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: Int? = null,
    val name: String? = null,
    val image: String? = null,
    val address: String? = null,
    val work: String? = null,
    val introduce: String? = null,
    val bookmarks: ImmutableList<Int>? = null,
)

@Serializable
data class UserResponse(val users: ImmutableList<UserDto>? = null)
