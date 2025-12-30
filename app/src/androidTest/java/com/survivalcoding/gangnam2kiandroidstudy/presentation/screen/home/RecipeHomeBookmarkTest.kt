package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.mockdata.MockRecipeData
import org.junit.Rule
import org.junit.Test

class RecipeHomeBookmarkTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun bookmark_toggle_state_preservation_test() {
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

        val viewModel = RecipeHomeViewModel(fakeRepository)

        composeTestRule.setContent {
            RecipeHomeRoot(viewModel = viewModel)
        }

        // Wait for data to load
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithContentDescription("bookmark disabled").fetchSemanticsNodes().isNotEmpty()
        }

        // Initially, bookmark should be disabled
        composeTestRule.onAllNodesWithContentDescription("bookmark disabled").onFirst().assertIsDisplayed()

        // When: Click bookmark button
        composeTestRule.onAllNodesWithContentDescription("bookmark disabled").onFirst().performClick()

        // Then: State should change to enabled (Recomposition triggered)
        composeTestRule.onAllNodesWithContentDescription("bookmark enabled").onFirst().assertIsDisplayed()

        // When: Click again
        composeTestRule.onAllNodesWithContentDescription("bookmark enabled").onFirst().performClick()

        // Then: State should toggle back to disabled
        composeTestRule.onAllNodesWithContentDescription("bookmark disabled").onFirst().assertIsDisplayed()
    }
}
