package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.core.di.coreModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.dataSourceModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.ingredientRepositoryModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.procedureRepositoryModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.useCaseModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.viewModelModule
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.mockdata.MockRecipeData
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class SearchRecipeIntegrationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val fakeRepository = FakeRecipeRepository()

    @Before
    fun setup() {
        // Stop the Koin instance started by AppApplication
        stopKoin()
        
        // Start Koin with our test configuration
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(
                coreModule,
                *dataSourceModule.toTypedArray(),
                ingredientRepositoryModule,
                procedureRepositoryModule,
                // Replace RecipeRepository with Fake
                module {
                    single<RecipeRepository> { fakeRepository }
                },
                *useCaseModule.toTypedArray(),
                *viewModelModule.toTypedArray()
            )
        }
    }
    
    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun searchRecipe_displaysResults_afterQuery() {
        runTest {
            // 1. Launch the screen
            composeTestRule.setContent {
                SearchRecipeRoot()
            }

            // 2. Type "Classic" in search bar (matches "Classic Greek Salad")
            composeTestRule.onNode(hasSetTextAction()).performTextInput("Classic")

            // 3. Wait for debounce (1000ms) + network (simulated) + UI update
            // We wait until the result text appears in the UI
            composeTestRule.waitUntil(timeoutMillis = 5000) {
                composeTestRule.onAllNodesWithText("Classic Greek Salad").fetchSemanticsNodes().isNotEmpty()
            }

            // 4. Verify results - Should be 3 based on MockRecipeData.recipeListThree
            composeTestRule.onAllNodesWithText("Classic Greek Salad").assertCountEquals(3)
        }
    }
}

class FakeRecipeRepository : RecipeRepository {
    var searchCalled = false

    override suspend fun findAll(): Result<List<Recipe>, NetworkError> {
        return Result.Success(emptyList())
    }

    override suspend fun search(
        query: String,
        time: String,
        rate: Double,
        category: String
    ): Result<List<Recipe>, NetworkError> {
        searchCalled = true
        // Filter mock data by query to match search behavior
        val filtered = MockRecipeData.recipeListThree.filter { 
            it.name.contains(query, ignoreCase = true) 
        }
        return Result.Success(filtered)
    }

    override suspend fun getRecipes(): List<Recipe> {
        return emptyList()
    }

    override suspend fun getRecipeById(recipeId: Int): Recipe? {
        return null
    }
}
