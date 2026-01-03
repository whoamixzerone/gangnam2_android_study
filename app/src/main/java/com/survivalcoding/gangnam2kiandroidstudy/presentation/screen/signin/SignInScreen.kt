package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.PasswordInputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SignInScreen(
    state: SignInState,
    modifier: Modifier = Modifier,
    onClickForgotPassword: () -> Unit = {},
    onAction: (SignInAction) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .padding(top = 94.dp)
    ) {
        Column {
            Text("Hello,", style = AppTextStyles.headerTextBold)
            Text("Welcome Back!", style = AppTextStyles.largeTextRegular)
        }

        InputField(
            modifier = Modifier.padding(top = 57.dp),
            label = "Email",
            placeholder = "Enter Email",
            value = state.email,
            onValueChange = { onAction(SignInAction.OnEmailValueChange(it)) }
        )
        PasswordInputField(
            modifier = Modifier.padding(top = 30.dp),
            label = "Enter Password",
            placeholder = "Enter Password",
            password = state.password,
            onValueChange = { onAction(SignInAction.OnPasswordValueChange(it)) }
        )

        Text(
            text = "Forgot Password?",
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(top = 20.dp)
                .clickable { onClickForgotPassword() },
            style = AppTextStyles.smallerTextRegular,
            color = AppColors.secondary100
        )

        BigButton(
            modifier = Modifier.padding(top = 25.dp),
            text = "Sign In",
            onClick = { onAction(SignInAction.OnSignInClick) }
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 60.dp)
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(modifier = Modifier.width(50.dp), thickness = 1.dp, color = AppColors.gray4)
            Text(
                text = "Or Sign in With",
                modifier = Modifier
                    .padding(horizontal = 7.dp),
                style = AppTextStyles.smallerTextBold,
                color = AppColors.gray4,
            )
            HorizontalDivider(modifier = Modifier.width(50.dp), thickness = 1.dp, color = AppColors.gray4)
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 101.dp)
                .padding(top = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .shadow(
                        elevation = 5.dp,
                        shape = RoundedCornerShape(10.dp),
                        ambientColor = Color(0x1A696969),
                        spotColor = Color(0x1A696969)
                    )
                    .background(color = AppColors.white, shape = RoundedCornerShape(10.dp))
                    .clickable { onAction(SignInAction.OnGoogleSignInClick) },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.icons8_google_240_1),
                    contentDescription = "google_icon",
                    modifier = Modifier
                        .size(24.dp)
                )
            }
            Spacer(Modifier.width(25.dp))

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .shadow(
                        elevation = 5.dp,
                        shape = RoundedCornerShape(10.dp),
                        ambientColor = Color(0x1A696969),
                        spotColor = Color(0x1A696969)
                    )
                    .background(color = AppColors.white, shape = RoundedCornerShape(10.dp))
                    .clickable { onAction(SignInAction.OnFaceBookSignInClick) },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.facebook),
                    contentDescription = "facebook_icon",
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 69.dp)
                .padding(top = 55.dp)
        ) {
            Text(text = "Don't have an account?", style = AppTextStyles.smallerTextBold)
            Spacer(Modifier.width(3.dp))
            Text(
                text = "Sign up",
                modifier = Modifier.clickable { onAction(SignInAction.OnSignUpClick) },
                style = AppTextStyles.smallerTextBold,
                color = AppColors.secondary100
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    SignInScreen(
        state = SignInState()
    )
}
