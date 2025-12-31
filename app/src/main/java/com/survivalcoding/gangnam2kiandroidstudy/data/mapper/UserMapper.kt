package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.UserDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.User
import kotlinx.collections.immutable.persistentListOf

fun UserDto.toModel(): User {
    return User(
        id = id ?: 0,
        name = name ?: "",
        image = image ?: "",
        address = address ?: "",
        work = work ?: "",
        introduce = introduce ?: "",
        bookmarks = bookmarks ?: persistentListOf()
    )
}