package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Route : NavKey {
    @Serializable
    data object Splash: Route

    @Serializable
    data object SignIn : Route

    @Serializable
    data object SignUp : Route

    @Serializable
    data class Main(val initialRoute: Route = Home) : Route

    @Serializable
    data object Home : Route

    @Serializable
    data object SavedRecipes : Route

    @Serializable
    data object Notifications : Route

    @Serializable
    data object Profile : Route

    @Serializable
    data object SearchRecipe : Route

    @Serializable
    data class RecipeDetail(val recipeId: Int) : Route
}
