package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import kotlinx.collections.immutable.toPersistentList

class GetRecipeDetailsUseCase(
    private val recipeRepository: RecipeRepository,
    private val ingredientRepository: IngredientRepository,
    private val procedureRepository: ProcedureRepository
) {

    suspend operator fun invoke(recipeId: Int): Result<Recipe, NetworkError> {
        return try {
            val recipeResult = recipeRepository.getRecipeById(recipeId)
                ?: return Result.Failure(
                    NetworkError.Unknown("해당 레시피가 존재하지 않습니다.")
                )

            val mergedRecipe = recipeResult.copy(
                ingredients = ingredientRepository.getIngredients(recipeId).toPersistentList(),
                procedures = procedureRepository.getProcedures(recipeId).toPersistentList()
            )

            Result.Success(mergedRecipe)
        } catch (e: NetworkError) {
            Result.Failure(e)
        }
    }

}
