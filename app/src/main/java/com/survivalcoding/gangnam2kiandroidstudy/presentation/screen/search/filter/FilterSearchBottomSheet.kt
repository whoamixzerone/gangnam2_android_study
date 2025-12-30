@file:OptIn(ExperimentalMaterial3Api::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search.filter

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterItemButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RatingButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search.SearchRecipeAction
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun FilterSearchBottomSheet(
    state: FilterSearchState,
    showBottomSheet: Boolean,
    sheetState: SheetState,
    modifier: Modifier = Modifier,
    onAction: (SearchRecipeAction) -> Unit = {},
) {
    val time = remember { mutableStateOf(state.time) }
    val rate = remember { mutableStateOf(state.rate) }
    val category = remember { mutableStateOf(state.category) }

    BackHandler(sheetState.isVisible) {
        onAction(SearchRecipeAction.CancelFilter)
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { onAction(SearchRecipeAction.CancelFilter) },
            sheetState = sheetState,
            shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
            containerColor = AppColors.white
        ) {
            Column(modifier = modifier.padding(horizontal = 30.dp)) {
                Spacer(Modifier.height(31.dp))
                Text(
                    text = "Filter Search",
                    modifier = Modifier.padding(horizontal = 113.dp),
                    style = AppTextStyles.smallTextBold.copy(fontWeight = FontWeight.SemiBold)
                )
                Spacer(Modifier.height(20.dp))

                Column {
                    Text(text = "Time", style = AppTextStyles.smallTextBold.copy(fontWeight = FontWeight.SemiBold))
                    Spacer(Modifier.height(10.dp))

                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        listOf("All", "Newest", "Oldest", "Popularity").forEach {
                            FilterItemButton(
                                text = it,
                                isSelected = it == time.value,
                                onClick = { time.value = it }
                            )
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))

                Column {
                    Text(text = "Rate", style = AppTextStyles.smallTextBold.copy(fontWeight = FontWeight.SemiBold))
                    Spacer(Modifier.height(10.dp))

                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        listOf("5", "4", "3", "2", "1").forEach {
                            RatingButton(
                                text = it,
                                isSelected = it.toDouble() == rate.value,
                                onClick = { rate.value = it.toDouble() }
                            )
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))

                Column {
                    Text(text = "Category", style = AppTextStyles.smallTextBold.copy(fontWeight = FontWeight.SemiBold))
                    Spacer(Modifier.height(10.dp))

                    FlowRow(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        RecipeCategory.entries.forEach {
                            FilterItemButton(
                                text = it.displayName,
                                isSelected = it == category.value,
                                onClick = { category.value = it }
                            )
                        }
                    }
                }
                Spacer(Modifier.height(30.dp))

                SmallButton(modifier = Modifier.padding(horizontal = 70.dp), text = "Filter") {
                    onAction(
                        SearchRecipeAction.ApplyFilter(
                            FilterSearchState(
                                time = time.value,
                                rate = rate.value,
                                category = category.value
                            )
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FilterSearchBottomSheetPreview() {
    val state = FilterSearchState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold { innerPadding ->
        FilterSearchBottomSheet(
            state = state,
            showBottomSheet = true,
            sheetState = sheetState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}