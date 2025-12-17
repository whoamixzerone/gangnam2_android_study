package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AssetDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import jakarta.inject.Inject

class IngredientRepositoryImpl @Inject constructor(
    private val dataSource: AssetDataSource
) : IngredientRepository {

    override suspend fun getIngredients(id: Int): List<Ingredient> {
        return dataSource.getIngredients().ingredients
            ?.filter { it.recipeId == id }
            ?.map { it.toModel() }
            ?: emptyList()
    }
}