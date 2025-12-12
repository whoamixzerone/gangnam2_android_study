package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SavedRecipeScreen(
    uiState: SavedRecipeState,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        when {
            uiState.error != null -> Text("데이터를 가져오는 중 에러가 발생했습니다.", style = AppTextStyles.largeTextBold)
            else -> RecipeItem(uiState.data)
        }
    }
}

@Composable
fun RecipeItem(recipes: List<Recipe>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        Spacer(Modifier.height(54.dp))
        Text("Saved recipes", modifier = Modifier.padding(horizontal = 93.dp), style = AppTextStyles.mediumTextBold)

        LazyColumn {
            items(recipes) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SavedRecipeScreenPreview() {
    Scaffold { innerPadding ->
        SavedRecipeScreen(
            uiState = SavedRecipeState(),
            modifier = Modifier.padding(innerPadding)
        )
    }
}