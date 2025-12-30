package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.mockdata.MockRecipeData
import org.junit.Rule
import org.junit.Test

class RecipeHomeIntegrationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    class FakeRecipeRepository(
        private val result: Result<List<Recipe>, NetworkError>
    ) : RecipeRepository {
        override suspend fun findAll(): Result<List<Recipe>, NetworkError> = result
        override suspend fun search(
            query: String,
            time: String,
            rate: Double,
            category: String
        ): Result<List<Recipe>, NetworkError> = result

        override suspend fun getRecipes(): List<Recipe> = (result as? Result.Success)?.data ?: emptyList()
        override suspend fun getRecipeById(recipeId: Int): Recipe? = null
    }

    @Test
    fun recipe_home_success_scenario_test() {
        // Given: Repository returns success
        val repository = FakeRecipeRepository(Result.Success(MockRecipeData.recipeListThree))
        val viewModel = RecipeHomeViewModel(repository)

        composeTestRule.setContent {
            RecipeHomeRoot(viewModel = viewModel)
        }

        // Then: Recipes should be displayed
        val firstRecipeName = MockRecipeData.recipeListThree.first().name
        composeTestRule.onAllNodesWithText(firstRecipeName).onFirst().assertIsDisplayed()
    }

    @Test
    fun recipe_home_error_scenario_test() {
        // Given: Repository returns failure
        val repository = FakeRecipeRepository(Result.Failure(NetworkError.Timeout))
        val viewModel = RecipeHomeViewModel(repository)

        composeTestRule.setContent {
            RecipeHomeRoot(viewModel = viewModel)
        }

        // Then: Error message should be displayed
        composeTestRule.onNodeWithText("데이터 요청 실패").assertIsDisplayed()
    }
}
