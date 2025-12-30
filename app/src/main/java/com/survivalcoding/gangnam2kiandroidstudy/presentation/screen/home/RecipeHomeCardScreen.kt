package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeHomeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.mockdata.MockRecipeData
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@Composable
fun RecipeHomeCardScreen(
    recipes: ImmutableList<Recipe>,
    savedRecipeIds: Set<Int>,
    modifier: Modifier = Modifier,
    onAction: (RecipeHomeAction) -> Unit = {},
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(Modifier.width(30.dp))

            LazyRow {
                items(recipes) { recipe ->
                    RecipeHomeCard(
                        recipe = recipe,
                        isSaved = savedRecipeIds.contains(recipe.id),
                        onAction = onAction
                    )
                    Spacer(Modifier.width(15.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RecipeHomeCardScreenPreview() {
    val recipes = MockRecipeData.recipeListThree.toPersistentList()

    Scaffold { innerPadding ->
        RecipeHomeCardScreen(
            recipes = recipes,
            savedRecipeIds = emptySet(),
            modifier = Modifier.padding(innerPadding)
        )
    }
}