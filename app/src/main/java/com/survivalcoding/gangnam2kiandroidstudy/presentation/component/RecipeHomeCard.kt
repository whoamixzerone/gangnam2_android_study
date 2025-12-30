package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.mockdata.MockRecipeData
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.RecipeHomeAction
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RecipeHomeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    isSaved: Boolean = false,
    onAction: (RecipeHomeAction) -> Unit = {},
) {
    Box(
        modifier = modifier
            .width(150.dp)
            .clickable { onAction(RecipeHomeAction.OnRecipeClick(recipe.id)) }
    ) {
        val painter = if (LocalInspectionMode.current) {
            painterResource(R.drawable.recipe)
        } else {
            rememberAsyncImagePainter(recipe.image)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 55.dp)
                .background(color = AppColors.gray4, shape = RoundedCornerShape(12.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 66.dp)
            ) {
                Text(
                    text = recipe.name,
                    style = AppTextStyles.smallTextBold.copy(fontWeight = FontWeight.SemiBold),
                    color = AppColors.gray1,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(19.dp))
                Row(
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Time", style = AppTextStyles.smallerTextRegular, color = AppColors.gray3)
                        Spacer(Modifier.height(5.dp))
                        Text(
                            text = recipe.time,
                            style = AppTextStyles.smallTextBold.copy(fontWeight = FontWeight.SemiBold),
                            color = AppColors.gray1
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onAction(RecipeHomeAction.ToggleBookmark(recipe.id)) }
                            .background(color = AppColors.white, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.union),
                            contentDescription = if (isSaved) "bookmark enabled" else "bookmark disabled",
                            modifier = Modifier.size(16.dp),
                            tint = if (isSaved) AppColors.primary80 else AppColors.gray3
                        )
                    }
                }
            }
        }

        Box(modifier = Modifier.padding(horizontal = 22.dp)) {
            Image(
                painter = painter,
                contentDescription = "${recipe.name} image",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(110.dp)
                    .shadow(elevation = 8.dp, shape = CircleShape, clip = false)
                    .clip(CircleShape)
            )

            Box(modifier = Modifier.offset(82.dp, 30.dp)) {
                Row(
                    modifier = Modifier
                        .size(45.dp, 23.dp)
                        .background(color = AppColors.secondary20, shape = RoundedCornerShape(20.dp)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.star_1),
                        contentDescription = "star icon",
                        modifier = Modifier
                            .size(10.dp),
                        tint = AppColors.rating
                    )

                    Spacer(Modifier.width(4.dp))
                    Text(text = "${recipe.rating}", style = AppTextStyles.smallerTextRegular)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RecipeHomeCardPreview() {
    val recipe = MockRecipeData.recipeListOne

    Scaffold { innerPadding ->
        RecipeHomeCard(
            recipe = recipe,
            modifier = Modifier.padding(innerPadding)
        )
    }
}