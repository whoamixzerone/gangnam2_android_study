package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.mockdata.MockRecipeData
import org.junit.Rule
import org.junit.Test

class SearchRecipeNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun list_to_detail_screen_navigation_test() {
        // Given
        val fakeRepository = object : RecipeRepository {
            override suspend fun findAll(): Result<List<Recipe>, NetworkError> {
                return Result.Success(MockRecipeData.recipeListThree)
            }

            override suspend fun search(
                query: String,
                time: String,
                rate: Double,
                category: String
            ): Result<List<Recipe>, NetworkError> {
                return Result.Success(MockRecipeData.recipeListThree)
            }

            override suspend fun getRecipes(): List<Recipe> {
                return MockRecipeData.recipeListThree
            }

            override suspend fun getRecipeById(recipeId: Int): Recipe? {
                return MockRecipeData.recipeListThree.find { it.id == recipeId }
            }
        }

        val viewModel = SearchRecipeViewModel(fakeRepository)

        composeTestRule.setContent {
            val backStack = rememberNavBackStack(Route.SearchRecipe)
            NavDisplay(
                backStack = backStack,
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator()
                ),
                entryProvider = entryProvider {
                    entry<Route.SearchRecipe> {
                        SearchRecipeRoot(
                            viewModel = viewModel,
                            onNavigateToDetail = { recipeId ->
                                backStack.add(Route.RecipeDetail(recipeId))
                            }
                        )
                    }
                    entry<Route.RecipeDetail> { key ->
                        // Simulate Detail Screen with the ID and a Back button
                        Text(text = "Detail Screen: ${key.recipeId}")
                        Button(onClick = { 
                            if (backStack.isNotEmpty()) {
                                backStack.removeAt(backStack.lastIndex)
                            }
                        }) {
                            Text("Back")
                        }
                    }
                }
            )
        }

        val targetRecipe = MockRecipeData.recipeListThree.first()
        
        // Wait for data and Verify List Screen is shown
        composeTestRule.onAllNodesWithText(targetRecipe.name).onFirst().assertExists()

        // When: Click a recipe item
        composeTestRule.onAllNodesWithText(targetRecipe.name).onFirst().performClick()

        // Then: Verify Navigation and ID passed correctly
        composeTestRule.onNodeWithText("Detail Screen: ${targetRecipe.id}").assertExists()

        // When: Navigate Back
        composeTestRule.onNodeWithText("Back").performClick()

        // Then: Verify List Screen is shown again (State preserved)
        composeTestRule.onAllNodesWithText(targetRecipe.name).onFirst().assertExists()
    }
}
