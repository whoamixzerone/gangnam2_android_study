package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.core.di.coreModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.useCaseModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.viewModelModule
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.mockdata.MockRecipeData
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail.RecipeDetailRoot
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
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeRepository = FakeRecipeRepository()
    private val fakeIngredientRepository = FakeIngredientRepository()
    private val fakeProcedureRepository = FakeProcedureRepository()

    @Before
    fun setup() {
        stopKoin()
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(
                coreModule,
                module {
                    single<RecipeRepository> { fakeRepository }
                    single<IngredientRepository> { fakeIngredientRepository }
                    single<ProcedureRepository> { fakeProcedureRepository }
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
            composeTestRule.setContent {
                SearchRecipeRoot()
            }

            composeTestRule.onNode(hasSetTextAction()).performTextInput("Classic")

            composeTestRule.waitUntil(timeoutMillis = 5000) {
                composeTestRule.onAllNodesWithText("Classic Greek Salad").fetchSemanticsNodes().isNotEmpty()
            }

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
        val filtered = MockRecipeData.recipeListThree.filter { 
            it.name.contains(query, ignoreCase = true) 
        }
        return Result.Success(filtered)
    }

    override suspend fun getRecipes(): List<Recipe> {
        return emptyList()
    }

    override suspend fun getRecipeById(recipeId: Int): Recipe? {
        return MockRecipeData.recipeListThree.find { it.id == recipeId }
    }
}

class FakeIngredientRepository : IngredientRepository {
    override suspend fun getIngredients(id: Int): List<Ingredient> {
        return emptyList()
    }
}

class FakeProcedureRepository : ProcedureRepository {
    override suspend fun getProcedures(id: Int): List<Procedure> {
        return emptyList()
    }
}
