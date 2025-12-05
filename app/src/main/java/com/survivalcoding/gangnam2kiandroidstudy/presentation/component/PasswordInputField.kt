package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun PasswordInputField(
    modifier: Modifier = Modifier,
    label: String = "Password",
    placeholder: String = "Enter Password",
    password: String = "",
    onValueChange: (String) -> Unit = {},
) {
    var showPassword by remember { mutableStateOf(value = false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            modifier = Modifier
                .fillMaxWidth()
                .height(21.dp),
            style = AppTextStyles.smallTextRegular
        )
        Spacer(Modifier.height(5.dp))

        OutlinedTextField(
            value = password,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            placeholder = {
                Text(
                    text = placeholder,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(17.dp),
                    style = AppTextStyles.smallerTextRegular,
                    color = AppColors.gray4
                )
            },
            textStyle = AppTextStyles.smallerTextRegular,
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedBorderColor = AppColors.primary80,
                unfocusedBorderColor = AppColors.gray4,
                disabledBorderColor = Color.Transparent
            ),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                if (showPassword) {
                    IconButton(onClick = { showPassword = false }) {
                        Icon(imageVector = Icons.Filled.Visibility, contentDescription = "hide_password")
                    }
                } else {
                    IconButton(onClick = { showPassword = true }) {
                        Icon(imageVector = Icons.Filled.VisibilityOff, contentDescription = "hide_password")
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PasswordInputFieldPreview() {
    PasswordInputField()
}