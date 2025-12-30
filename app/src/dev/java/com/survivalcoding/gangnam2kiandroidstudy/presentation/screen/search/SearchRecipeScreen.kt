@file:OptIn(ExperimentalMaterial3Api::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSettingButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeSearchCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Search
import com.survivalcoding.gangnam2kiandroidstudy.presentation.mockdata.MockRecipeData
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search.filter.FilterSearchBottomSheet
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SearchRecipeScreen(
    state: SearchRecipeState,
    sheetState: SheetState,
    modifier: Modifier = Modifier,
    onAction: (SearchRecipeAction) -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 30.dp)) {
            Spacer(Modifier.height(54.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(27.dp),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow Back Icon",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(69.dp))
                Text(
                    text = "Search recipe (dev)",
                    style = AppTextStyles.mediumTextBold.copy(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                )
            }
            Spacer(Modifier.height(17.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Search(
                    modifier = Modifier.weight(1f),
                    placeholder = "Search recipe",
                    value = state.query,
                    isSearchEnabled = state.isSearchEnabled,
                    onClick = { onAction(SearchRecipeAction.OnSearchDone) },
                    onValueChange = { onAction(SearchRecipeAction.UpdateQuery(it)) }
                )
                Spacer(Modifier.width(20.dp))

                FilterSettingButton { onAction(SearchRecipeAction.OnFilterSettingClick) }
            }
            Spacer(Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (state.filterRecipes.isNotEmpty()) "Search Result" else "Recent Search",
                    modifier = Modifier
                        .weight(1f),
                    style = AppTextStyles.normalTextBold
                )

                Text(
                    text = if (state.filterRecipes.isNotEmpty()) "${state.filterRecipes.size} results" else "",
                    style = AppTextStyles.smallerTextRegular,
                    color = AppColors.gray3
                )
            }
            Spacer(Modifier.height(20.dp))

            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    val recipes = state.filterRecipes.ifEmpty { state.recipes }

                    items(recipes) { recipe ->
                        RecipeSearchCard(
                            recipe = recipe,
                            onAction = onAction
                        )
                    }
                }
            }

            FilterSearchBottomSheet(
                state = state.filterSearchState,
                showBottomSheet = state.showBottomSheet,
                sheetState = sheetState,
                onAction = onAction,
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchRecipeScreenPreview() {
    val state = SearchRecipeState(
        recipes = MockRecipeData.recipeListThree,
        filterRecipes = MockRecipeData.recipeListThree
    )
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold { innerPadding ->
        SearchRecipeScreen(
            state = state,
            sheetState = sheetState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}