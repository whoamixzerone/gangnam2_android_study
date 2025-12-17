package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun Tabs(
    modifier: Modifier = Modifier,
    labels: List<String>,
    selectedIndex: Int = 0,
    onValueChange: (Int) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(58.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            repeat(labels.size) { index ->
                Column(
                    modifier = Modifier
                        .size(150.dp, 33.dp)
                        .background(
                            color = if (index == selectedIndex) AppColors.primary100 else AppColors.white,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable { onValueChange(index) },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = labels[index],
                        modifier = Modifier.size(132.dp, 17.dp),
                        style = AppTextStyles.smallerTextBold,
                        color = if (index == selectedIndex) AppColors.white else AppColors.primary80,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.width(15.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TabsPreview1() {
    Tabs(labels = listOf("Label", "Label"))
}

@Preview(showBackground = true)
@Composable
private fun TabsPreview2() {
    Tabs(labels = listOf("Label", "Label"), selectedIndex = 1)
}