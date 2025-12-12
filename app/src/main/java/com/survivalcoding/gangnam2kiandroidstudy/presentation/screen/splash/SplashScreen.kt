package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.MediumButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SplashScreen(modifier: Modifier = Modifier, navigateStartCooking: () -> Unit = {}) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.recipe_main),
            contentDescription = "recipe app start image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0x66000000), Color(0xFF000000))
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(modifier = Modifier.padding(horizontal = 83.dp, vertical = 60.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier.padding(horizontal = 65.dp)) {
                        Image(
                            painter = painterResource(R.drawable.image_11),
                            contentDescription = "recipe cap icon",
                            modifier = Modifier
                                .size(79.dp)
                        )
                    }
                    Spacer(Modifier.height(14.dp))
                    Text(
                        text = "100K+ Premium Recipe",
                        modifier = Modifier.fillMaxWidth(),
                        style = AppTextStyles.mediumTextBold.copy(fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
                        color = AppColors.white
                    )
                }
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 50.dp)
                    .padding(top = 222.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier.padding(horizontal = 31.dp), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Get Cooking",
                            style = AppTextStyles.headerTextBold.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 50.sp,
                                lineHeight = 50.sp * 1.2f
                            ),
                            color = AppColors.white,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(Modifier.height(20.dp))

                    Text(
                        "Simple way to find Tasty Recipe",
                        style = AppTextStyles.normalTextRegular,
                        color = AppColors.white
                    )
                }
            }

            MediumButton(
                modifier = Modifier
                    .padding(horizontal = 66.dp)
                    .padding(top = 64.dp),
                text = "Start Cooking",
                onClick = navigateStartCooking
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreen()
}