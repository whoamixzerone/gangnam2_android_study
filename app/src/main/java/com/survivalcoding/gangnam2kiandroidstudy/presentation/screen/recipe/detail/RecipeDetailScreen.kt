package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.MinimalDropdownMenu
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeDetailCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeLinkDialog
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Tabs
import com.survivalcoding.gangnam2kiandroidstudy.presentation.mockdata.MockRecipeData
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RecipeDetailScreen(
    uiState: RecipeDetailState,
    modifier: Modifier = Modifier,
    onAction: (RecipeDetailAction) -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(Modifier.height(54.dp))

            Column(modifier = Modifier.padding(horizontal = 30.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { onAction(RecipeDetailAction.OnArrowBackClick) }
                    )
                    Spacer(Modifier.weight(1f))
                    MinimalDropdownMenu(
                        expanded = uiState.expandedMenu,
                        onAction = onAction
                    )
                }

                uiState.recipe?.let { recipe ->
                    Spacer(Modifier.height(10.dp))
                    RecipeDetailCard(recipe = recipe)

                    Spacer(Modifier.height(10.dp))
                    Row {
                        Spacer(Modifier.width(5.dp))
                        Text(
                            text = recipe.name,
                            modifier = Modifier.weight(1f),
                            style = AppTextStyles.smallTextBold.copy(fontWeight = FontWeight.SemiBold),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(Modifier.width(18.dp))
                        Text(
                            text = "(13k Reviews)",
                            style = AppTextStyles.smallTextRegular,
                            color = AppColors.gray3,
                            maxLines = 1
                        )
                    }

                    Spacer(Modifier.height(10.dp))
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.chef_profile),
                            contentDescription = "chef profile",
                            modifier = Modifier
                                .clip(shape = CircleShape)
                                .size(40.dp)
                        )

                        Spacer(Modifier.width(10.dp))

                        Column {
                            Text(
                                text = "Laura wilson",
                                style = AppTextStyles.smallTextBold
                            )

                            Spacer(Modifier.height(2.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(R.drawable.filled_location),
                                    contentDescription = "Location",
                                    tint = AppColors.primary80,
                                    modifier = Modifier.size(17.dp)
                                )
                                Spacer(Modifier.width(1.dp))
                                Text(
                                    text = "Lagos, Nigeria",
                                    style = AppTextStyles.smallerTextRegular,
                                    color = AppColors.gray3
                                )
                            }
                        }

                        Spacer(Modifier.weight(1f))

                        Box(modifier = Modifier.width(85.dp)) {
                            SmallButton(
                                text = "Follow",
                                onClick = {
                                    onAction(RecipeDetailAction.OnFollowClick(1)) // TODO 사용자 ID가 없어서 하드코딩
                                }
                            )
                        }
                    }

                    Spacer(Modifier.height(8.dp))
                    Tabs(
                        labels = uiState.tabLabels,
                        selectedIndex = uiState.selectedTabIndex,
                        onValueChange = { index ->
                            onAction(RecipeDetailAction.UpdateTabInfo(index))
                        }
                    )

                    Spacer(Modifier.height(22.dp))
                    when (uiState.selectedTabIndex) {
                        0 -> IngredientScreen(ingredients = recipe.ingredients)
                        1 -> ProcedureScreen(procedures = recipe.procedures)
                    }
                } ?: Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }

            }

        }

        if (uiState.expandedMenu) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(10.dp)
                    .background(AppColors.black.copy(alpha = 0.10f))
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onAction(RecipeDetailAction.OnDropdownMenuClick(false))
                    }
            )
        }

        if (uiState.visibleShare) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                RecipeLinkDialog(
                    title = "Recipe Link",
                    description = "Copy recipe link and share your recipe link with friends and family.",
                    link = uiState.shareLink,
                    onCloseClick = { onAction(RecipeDetailAction.OnLinkDialogCloseClick) },
                    onCopyLinkClick = { onAction(RecipeDetailAction.OnCopyLinkClick(it)) }
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(10.dp)
                    .background(AppColors.black.copy(alpha = 0.10f))
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onAction(RecipeDetailAction.OnShareClick)
                    }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RecipeDetailScreenPreview() {
    val uiState = RecipeDetailState(
        recipe = MockRecipeData.recipeListOne
    )

    RecipeDetailScreen(
        uiState = uiState,
    )
}