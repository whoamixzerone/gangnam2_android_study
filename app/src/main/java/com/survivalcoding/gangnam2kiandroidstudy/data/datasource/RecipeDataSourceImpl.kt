package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.core.HttpClientFactory
import com.survivalcoding.gangnam2kiandroidstudy.core.Response
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import io.ktor.util.toMap
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : RecipeDataSource {
    override suspend fun findAll(): Response<RecipeResponse> = withContext(Dispatchers.IO) {
        val response = httpClient.get(BASE_URL)

        if (!response.status.isSuccess()) {
            return@withContext Response(
                headers = response.headers.toMap(),
                statusCode = response.status.value
            )
        }

        Response(
            headers = response.headers.toMap(),
            statusCode = response.status.value,
            body = response.body()
        )
    }

    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com/junsuk5/mock_json/refs/heads/v2/recipe/recipes.json"
    }
}