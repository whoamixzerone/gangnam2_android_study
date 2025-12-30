package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import android.net.Uri

sealed class DeepLink {
    object SavedRecipes : DeepLink()
    data class RecipeDetail(val id: Int) : DeepLink()

    companion object {
        fun fromUri(uri: Uri): DeepLink? {
            val host = uri.host ?: return null
            val scheme = uri.scheme ?: return null
            val segments = uri.pathSegments

            if (scheme !in listOf("myapp", "http", "https")) return null
            if (host !in listOf("recipes", "gangnam2-android.web.app")) return null

            return when {
                segments.contains("saved") -> SavedRecipes

                segments.lastOrNull()?.toIntOrNull() != null -> {
                    val recipeId = segments.last().toInt()
                    RecipeDetail(recipeId)
                }

                else -> null
            }
        }
    }
}
