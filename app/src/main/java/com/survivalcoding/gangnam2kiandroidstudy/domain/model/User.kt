package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import kotlinx.collections.immutable.ImmutableList

data class User(
    val id: Int,
    val name: String,
    val image: String,
    val address: String,
    val work: String,
    val introduce: String,
    val bookmarks: ImmutableList<Int>
)
