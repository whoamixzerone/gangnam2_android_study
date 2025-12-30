package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import kotlinx.collections.immutable.ImmutableList

data class Recipe(
    val id: Int,
    val category: String,
    val name: String,
    val image: String,
    val chef: String,
    val time: String,
    val rating: Double,
    val ingredients: ImmutableList<Ingredient>,
    val procedures: ImmutableList<Procedure>,
)
