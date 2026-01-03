package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
fun SignUpScreen(
    state: SignUpState,
    modifier: Modifier = Modifier,
    onAction: (SignUpAction) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .padding(top = 54.dp)
    ) {
        Column(modifier = Modifier.width(195.dp)) {
            Text(text = "Create an account", style = AppTextStyles.largeTextBold)
            Spacer(Modifier.height(5.dp))
            Text(
                text = "Let's help you set up your account, it won't take long.",
                style = AppTextStyles.smallerTextRegular
            )
        }

        InputField(
            modifier = Modifier.padding(top = 20.dp),
            label = "Name",
            placeholder = "Enter Name",
            value = state.name,
            onValueChange = { onAction(SignUpAction.OnNameValueChange(it)) }
        )
        InputField(
            modifier = Modifier.padding(top = 20.dp),
            label = "Email",
            placeholder = "Enter Email",
            value = state.email,
            onValueChange = { onAction(SignUpAction.OnEmailValueChange(it)) }
        )
        PasswordInputField(
            modifier = Modifier.padding(top = 20.dp),
            label = "Password",
            placeholder = "Enter Password",
            password = state.password,
            onValueChange = { onAction(SignUpAction.OnPasswordValueChange(it)) }
        )
        PasswordInputField(
            modifier = Modifier.padding(top = 20.dp),
            label = "Confirm Password",
            placeholder = "Retype Password",
            password = state.confirmPassword,
            onValueChange = { onAction(SignUpAction.OnConfirmPasswordValueChange(it)) }
        )

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = state.isChecked,
                onCheckedChange = { onAction(SignUpAction.OnCheckBoxClick) },
                modifier = Modifier
                    .size(17.dp),
                colors = CheckboxDefaults.colors(
                    checkedColor = AppColors.secondary100,
                    uncheckedColor = AppColors.secondary100
                )
            )
            Spacer(Modifier.width(5.dp))
            Text(
                text = "Accept terms & Condition",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .clickable { onAction(SignUpAction.OnCheckBoxClick) },
                style = AppTextStyles.smallerTextRegular,
                color = AppColors.secondary100
            )
        }

        BigButton(
            modifier = Modifier.padding(top = 26.dp),
            text = "Sign Up",
            onClick = { onAction(SignUpAction.OnSignUpClick) }
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
                text = "Or Sign up With",
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
                    .background(color = AppColors.white, shape = RoundedCornerShape(10.dp)),
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
                    .background(color = AppColors.white, shape = RoundedCornerShape(10.dp)),
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
                .padding(horizontal = 83.dp)
                .padding(top = 20.dp)
        ) {
            Text(text = "Already a member?", style = AppTextStyles.smallerTextBold)
            Spacer(Modifier.width(3.dp))
            Text(
                text = "Sign in",
                modifier = Modifier.clickable { onAction(SignUpAction.OnSignInClick) },
                style = AppTextStyles.smallerTextBold,
                color = AppColors.secondary100
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(
        state = SignUpState()
    )
}
