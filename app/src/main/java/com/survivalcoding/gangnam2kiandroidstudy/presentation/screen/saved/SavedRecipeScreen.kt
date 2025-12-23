package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.mockdata.MockRecipeData
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SavedRecipeScreen(
    uiState: SavedRecipeState,
    scrollState: LazyListState,
    modifier: Modifier = Modifier,
    onAction: (SavedRecipeAction) -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            uiState.isLoading -> CircularProgressIndicator()
            uiState.error != null -> Text(
                text = "데이터를 가져오는 중 에러가 발생했습니다.",
                style = AppTextStyles.largeTextBold
            )

            else -> RecipeItem(
                recipes = uiState.data,
                scrollState = scrollState,
                onAction = onAction
            )
        }
    }
}

@Composable
fun RecipeItem(
    recipes: List<Recipe>,
    scrollState: LazyListState,
    onAction: (SavedRecipeAction) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        Spacer(Modifier.height(54.dp))
        Text(
            text = "Saved recipes",
            modifier = Modifier.padding(horizontal = 93.dp),
            style = AppTextStyles.mediumTextBold
        )

        Spacer(Modifier.height(10.dp))
        LazyColumn(
            state = scrollState,
        ) {
            items(recipes) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    modifier = Modifier
                        .clickable { onAction(SavedRecipeAction.OnRecipeItemClick(recipe.id)) }
                        .padding(vertical = 20.dp),
                    onUnBookmark = { onAction(SavedRecipeAction.OnUnBookmark(it)) }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SavedRecipeScreenPreview() {
    val uiState = SavedRecipeState(
        data = MockRecipeData.recipeListThree
    )
    val scrollState = rememberLazyListState()

    Scaffold { innerPadding ->
        SavedRecipeScreen(
            uiState = uiState,
            scrollState = scrollState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
