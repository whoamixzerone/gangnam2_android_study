package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetRecipeDetailsUseCaseTest {

    private lateinit var recipeRepository: RecipeRepository
    private lateinit var ingredientRepository: IngredientRepository
    private lateinit var procedureRepository: ProcedureRepository

    private lateinit var useCase: GetRecipeDetailsUseCase

    @Before
    fun setUp() {
        recipeRepository = mockk()
        ingredientRepository = mockk()
        procedureRepository = mockk()

        useCase = GetRecipeDetailsUseCase(
            recipeRepository,
            ingredientRepository,
            procedureRepository
        )
    }

    @Test
    fun `Result Success로 데이터를 정상적으로 반환한다`() = runTest {
        // given
        val recipeId = 1

        val baseRecipe = Recipe(
            1,
            "Indian",
            "Steak with tomato sauce and bulgur rice.",
            "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            "James Milner",
            "20 min",
            4.0,
            persistentListOf(),
            persistentListOf()
        )

        val ingredients = listOf(
            Ingredient(
                1,
                "Tomatos",
                "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
                "500g",
                1
            )
        )

        val procedures = listOf(
            Procedure(
                step = 1,
                content = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
                recipeId = 1
            )
        )

        coEvery { recipeRepository.getRecipeById(recipeId) } returns baseRecipe
        coEvery { ingredientRepository.getIngredients(recipeId) } returns ingredients
        coEvery { procedureRepository.getProcedures(recipeId) } returns procedures

        // when
        val result = useCase.invoke(recipeId)

        // then
        assertTrue(result is Result.Success)

        val data = (result as Result.Success).data
        assertEquals(ingredients, data.ingredients)
        assertEquals(procedures, data.procedures)
    }

    @Test
    fun `레시피가 존재하지 않는 경우 Result Failure을 반환한다`() = runTest {
        // given
        val recipeId = 1

        coEvery { recipeRepository.getRecipeById(recipeId) } returns null

        // when
        val result = useCase(recipeId)

        // then
        assertTrue(result is Result.Failure)
        assertEquals(
            "해당 레시피가 존재하지 않습니다.",
            (result as Result.Failure).error.message
        )

        coVerify(exactly = 0) { ingredientRepository.getIngredients(any()) }
        coVerify(exactly = 0) { procedureRepository.getProcedures(any()) }
    }

}