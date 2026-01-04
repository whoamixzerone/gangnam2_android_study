package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.User
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.UserRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.mockdata.MockRecipeData
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
        
        val fakeUserRepository = object : UserRepository {
            private val _user = MutableStateFlow<User?>(
                User(
                    id = 1,
                    name = "",
                    image = "",
                    address = "",
                    work = "",
                    introduce = "",
                    bookmarks = persistentListOf()
                )
            )
            
            override fun loadById(id: Int): Flow<User?> = _user.asStateFlow()
            
            override suspend fun save(user: User) {
                _user.value = user
            }
            
            override suspend fun updateSavedRecipe(id: Int, recipeId: Int) {
                _user.update { currentUser ->
                    currentUser?.let {
                        val currentList = it.bookmarks
                        val newList = if (currentList.contains(recipeId)) {
                            (currentList - recipeId).toPersistentList()
                        } else {
                            (currentList + recipeId).toPersistentList()
                        }
                        it.copy(bookmarks = newList)
                    }
                }
            }
        }

        val fakeBookmarkRepository = object : BookmarkRepository {
            override fun updateBookmarkRecipe(id: Int): Flow<Result<Unit, String>> {
                TODO("Not yet implemented")
            }

            override fun getBookmarks(): Flow<Result<List<Int>, String>> {
                TODO("Not yet implemented")
            }
        }

        val viewModel = RecipeHomeViewModel(fakeRepository, fakeUserRepository, fakeBookmarkRepository)

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