package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSettingButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCategorySelector
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Search
import com.survivalcoding.gangnam2kiandroidstudy.presentation.mockdata.MockRecipeData
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RecipeHomeScreen(
    state: RecipeHomeState,
    modifier: Modifier = Modifier,
    onAction: (RecipeHomeAction) -> Unit = {},
) {
    Column(modifier = modifier) {
        Spacer(Modifier.height(64.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(text = "Hello Jega", style = AppTextStyles.largeTextBold.copy(fontWeight = FontWeight.SemiBold))
                Spacer(Modifier.height(5.dp))

                Text(
                    text = "What are you cooking today?",
                    style = AppTextStyles.smallerTextRegular,
                    color = AppColors.gray3
                )
            }
            Spacer(Modifier.width(80.dp))

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color = AppColors.secondary40, shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "profile avatar image"
                )
            }
        }

        Spacer(Modifier.height(30.dp))
        Row(
            modifier = Modifier.padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Search(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onAction(RecipeHomeAction.OnSearchClick) },
                placeholder = "Search recipe"
            )
            Spacer(Modifier.width(20.dp))
            FilterSettingButton(modifier = Modifier)
        }

        Spacer(Modifier.height(15.dp))
        RecipeCategorySelector(
            modifier = Modifier,
            selectCategory = state.selectedCategory,
            onSelectCategory = {
                onAction(RecipeHomeAction.SelectedCategory(it))
            }
        )

        Spacer(Modifier.height(15.dp))
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("데이터 요청 실패", style = AppTextStyles.largeTextBold)
                }
            }

            else -> RecipeHomeCardScreen(
                recipes = state.recipes,
                savedRecipeIds = state.savedRecipeIds,
                modifier = Modifier.padding(top = 15.dp),
                onAction = onAction
            )
        }

        Spacer(Modifier.height(20.dp))
        Text(
            text = "New Recipes",
            modifier = Modifier.padding(horizontal = 30.dp),
            style = AppTextStyles.normalTextBold.copy(fontWeight = FontWeight.SemiBold)
        )

        Spacer(Modifier.height(5.dp))
        RecipeHomeRatingScreen(
            recipes = state.recipes,
            modifier = Modifier,
            onAction = onAction
        )

        Spacer(Modifier.height(6.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RecipeHomeScreenPreview() {
    val state = RecipeHomeState(
        recipes = MockRecipeData.recipeListThree,
        isLoading = false
    )

    Scaffold { innerPadding ->
        RecipeHomeScreen(
            state = state,
            modifier = Modifier.padding(innerPadding)
        )
    }
}