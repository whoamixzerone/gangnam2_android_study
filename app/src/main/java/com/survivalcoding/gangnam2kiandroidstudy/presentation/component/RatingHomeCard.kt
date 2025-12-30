package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
fun RatingHomeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    onAction: (RecipeHomeAction) -> Unit = {},
) {
    Box(
        modifier = modifier
            .size(251.dp, 127.dp)
            .clickable { onAction(RecipeHomeAction.OnRecipeClick(recipe.id)) }
    ) {
        val painter = if (LocalInspectionMode.current) {
            painterResource(R.drawable.recipe_rate)
        } else {
            rememberAsyncImagePainter(recipe.image)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .height(95.dp)
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(10.dp),
                    clip = false,
                    ambientColor = Color(0x1A000000),
                    spotColor = Color(0x1A000000)
                )
                .background(color = AppColors.white, shape = RoundedCornerShape(10.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 9.3.dp)
            ) {
                Spacer(Modifier.height(10.dp))
                Text(
                    text = recipe.name,
                    modifier = Modifier.width(139.44.dp),
                    style = AppTextStyles.smallTextBold.copy(fontWeight = FontWeight.SemiBold),
                    color = AppColors.gray1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(5.dp))
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    RatingItem(recipe.rating.toInt(), modifier = Modifier)
                }

                Spacer(Modifier.height(10.dp))
                Row {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.chef_profile),
                            contentDescription = "chef profile",
                            modifier = Modifier
                                .clip(shape = CircleShape)
                                .size(25.dp)
                        )

                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "By ${recipe.chef}",
                            modifier = Modifier.padding(vertical = 4.dp),
                            style = AppTextStyles.smallerTextRegular,
                            color = AppColors.gray3
                        )
                    }

                    Row(modifier = Modifier.height(24.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.timer),
                            contentDescription = "timer icon",
                            modifier = Modifier.size(17.dp),
                            tint = AppColors.gray3
                        )
                        Spacer(Modifier.width(5.dp))
                        Text(
                            text = "${recipe.time}s",
                            style = AppTextStyles.smallerTextRegular,
                            color = AppColors.gray3
                        )
                    }
                }
            }

        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 9.3.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = painter,
                contentDescription = "${recipe.name} image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp, 86.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = CircleShape,
                        clip = false,
                        ambientColor = Color(0x26202020),
                        spotColor = Color(0x26202020)
                    )
                    .clip(CircleShape)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RateHomeCardPreview() {
    val recipe = MockRecipeData.recipeListOne

    Scaffold { innerPadding ->
        RatingHomeCard(
            recipe = recipe,
            modifier = Modifier.padding(innerPadding)
        )
    }
}