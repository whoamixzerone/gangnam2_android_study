package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

fun RecipeDto.toModel(): Recipe {
    return Recipe(
        id = id ?: 0,
        category = category ?: "",
        name = name ?: "",
        image = image ?: "",
        chef = chef ?: "",
        time = time ?: "",
        rating = rating ?: 0.0,
        ingredients = ingredients?.filter { it.id != null }
            ?.map { it.toModel() }
            ?.toPersistentList()
            ?: persistentListOf(),
        procedures = procedures?.filter { it.recipeId != null }
            ?.map { it.toModel() }
            ?.toPersistentList()
            ?: persistentListOf()
    )
}