package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SmallButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val backgroundColor = if (isPressed) AppColors.gray4 else AppColors.primary100

    Box(
        modifier = modifier
            .size(width = 174.dp, height = 37.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(10.dp))
            .clickable(interactionSource = interactionSource) {
                onClick()
            },
        contentAlignment = Alignment.Center,
    ) {
        Row {
            Column(
                modifier = Modifier.size(width = 114.dp, height = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = text, style = AppTextStyles.smallerTextBold, color = AppColors.white)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SmallButtonPreview() {
    SmallButton(text = "Button") { }
}
