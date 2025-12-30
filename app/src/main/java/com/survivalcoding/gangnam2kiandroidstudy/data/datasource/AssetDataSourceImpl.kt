package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import android.content.Context
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.IngredientResponse
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.ProcedureResponse
import kotlinx.serialization.json.Json

// TODO 파일에서 가져오기 때문에 Response는 나중에 추가
class AssetDataSourceImpl(private val context: Context) : AssetDataSource {
    override suspend fun getIngredients(): IngredientResponse {
        val jsonString = context.assets
            .open("ingredients.json")
            .bufferedReader()
            .use { it.readText() }

        return Json.decodeFromString(jsonString)
    }

    override suspend fun getProcedures(): ProcedureResponse {
        val jsonString = context.assets
            .open("procedure.json")
            .bufferedReader()
            .use { it.readText() }

        return Json.decodeFromString(jsonString)
    }
}
