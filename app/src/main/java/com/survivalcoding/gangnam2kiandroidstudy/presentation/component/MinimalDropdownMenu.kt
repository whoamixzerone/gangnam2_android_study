package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail.RecipeDetailAction
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun MinimalDropdownMenu(
    expanded: Boolean,
    modifier: Modifier = Modifier,
    onAction: (RecipeDetailAction) -> Unit = {},
) {
    Box(
        modifier = modifier.size(24.dp)
    ) {
        IconButton(
            onClick = { onAction(RecipeDetailAction.OnDropdownMenuClick(true)) }
        ) {
            Icon(
                painter = painterResource(R.drawable.meatballs_menu),
                contentDescription = "Meatballs Menu",
                modifier = Modifier
                    .size(24.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onAction(RecipeDetailAction.OnDropdownMenuClick(false)) },
            modifier = Modifier.width(164.dp),
            shape = RoundedCornerShape(8.dp),
            containerColor = AppColors.white,
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = "share", style = AppTextStyles.smallTextRegular, color = AppColors.labelColour)
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_filled_share),
                        contentDescription = "Share Menu",
                        modifier = Modifier.size(20.dp),
                        tint = AppColors.labelColour
                    )
                },
                onClick = {
                    onAction(RecipeDetailAction.OnDropdownMenuClick(false))
                    onAction(RecipeDetailAction.OnShareClick)
                }
            )
            DropdownMenuItem(
                text = {
                    Text(text = "Rate Recipe", style = AppTextStyles.smallTextRegular, color = AppColors.labelColour)
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.star_1),
                        contentDescription = "Rate Recipe Menu",
                        modifier = Modifier.size(20.dp),
                        tint = AppColors.labelColour
                    )
                },
                onClick = {}
            )
            DropdownMenuItem(
                text = {
                    Text(text = "Review", style = AppTextStyles.smallTextRegular, color = AppColors.labelColour)
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_filled_review),
                        contentDescription = "Review Menu",
                        modifier = Modifier.size(20.dp),
                        tint = AppColors.labelColour
                    )
                },
                onClick = {}
            )
            DropdownMenuItem(
                text = {
                    Text(text = "Unsave", style = AppTextStyles.smallTextRegular, color = AppColors.labelColour)
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_filled_save),
                        contentDescription = "Unsave Menu",
                        modifier = Modifier.size(20.dp),
                        tint = AppColors.labelColour
                    )
                },
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MinimalDropdownMenuPreview() {
    MinimalDropdownMenu(
        expanded = false,
        modifier = Modifier.padding(30.dp)
    )
}