package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved

import app.cash.turbine.test
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.exception.NetworkError
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SavedRecipeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private lateinit var repository: RecipeRepository
    private lateinit var viewModel: SavedRecipeViewModel

    val recipes = listOf(
        Recipe(
            category = "Indian",
            id = 1,
            name = "Traditional spare ribs baked",
            image = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            chef = "Chef John",
            time = "20 min",
            rating = 4.0
        ),
        Recipe(
            category = "Asian",
            id = 2,
            name = "Spice roasted chicken with flavored rice",
            image = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
            chef = "Mark Kelvin",
            time = "20 min",
            rating = 4.0
        ),
        Recipe(
            category = "Chinese",
            id = 3,
            name = "Spicy fried rice mix chicken bali",
            image = "https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
            chef = "Spicy Nelly",
            time = "20 min",
            rating = 4.0
        ),
        Recipe(
            category = "Japanese",
            id = 4,
            name = "Ttekbokki",
            image = "https://cdn.pixabay.com/photo/2017/07/27/16/48/toppokki-2545943_1280.jpg",
            chef = "Kim Dahee",
            time = "30 min",
            rating = 5.0
        ),
        Recipe(
            category = "American",
            id = 5,
            name = "Grilled salmon with avocado salsa",
            image = "https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_1280.jpg",
            chef = "Alice Johnson",
            time = "25 min",
            rating = 4.5
        ),
        Recipe(
            category = "British",
            id = 6,
            name = "Beef Wellington",
            image = "https://cdn.pixabay.com/photo/2019/10/22/10/11/beef-wellington-4568239_1280.jpg",
            chef = "Gordon Ramsay",
            time = "45 min",
            rating = 5.0
        ),
        Recipe(
            category = "Italian",
            id = 7,
            name = "Classic Margherita Pizza",
            image = "https://cdn.pixabay.com/photo/2019/05/15/18/56/pizza-4205701_1280.jpg",
            chef = "Mario Batali",
            time = "15 min",
            rating = 4.3
        ),
        Recipe(
            category = "Japanese",
            id = 8,
            name = "Sushi Platter",
            image = "https://cdn.pixabay.com/photo/2017/10/15/11/41/sushi-2853382_1280.jpg",
            chef = "Jiro Ono",
            time = "60 min",
            rating = 4.8
        ),
        Recipe(
            category = "French",
            id = 9,
            name = "French Onion Soup",
            image = "https://cdn.pixabay.com/photo/2016/03/03/16/19/food-1234483_1280.jpg",
            chef = "Julia Child",
            time = "40 min",
            rating = 4.6
        ),
        Recipe(
            category = "French",
            id = 10,
            name = "Chocolate Lava Cake",
            image = "https://cdn.pixabay.com/photo/2016/11/22/18/52/cake-1850011_1280.jpg",
            chef = "Paul Hollywood",
            time = "30 min",
            rating = 4.9
        )
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `데이터 요청에 성공하면 Result Success가 응답된다`() = testScope.runTest {
        // given
        coEvery { repository.findAll() } returns Result.Success(recipes)

        viewModel = SavedRecipeViewModel(repository)

        // then
        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(emptyList<Recipe>(), state.data)

            val updated = awaitItem()
            assertEquals(recipes, updated.data)
        }
    }

    @Test
    fun `데이터 요청에 실패하면 Result Failure가 응답된다`() = testScope.runTest {
        // given
        val error = NetworkError.NetworkUnavailable
        coEvery { repository.findAll() } returns Result.Failure(error)

        viewModel = SavedRecipeViewModel(repository)

        // then
        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(emptyList<Recipe>(), state.data)

            val updated = awaitItem()
            assertEquals(error.toString(), updated.error)
        }
    }
}