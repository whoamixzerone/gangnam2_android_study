package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    label: String = "Label",
    placeholder: String = "Placeholder",
    value: String = "",
    onValueChange: (String) -> Unit = {},
) {
    var borderColor by remember { mutableStateOf(AppColors.gray4) }

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
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .border(width = 1.5.dp, color = borderColor, shape = RoundedCornerShape(10.dp))
                .onFocusChanged { focusState ->
                    borderColor = if (focusState.isFocused) AppColors.primary80 else AppColors.gray4
                },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = AppColors.white,
                unfocusedContainerColor = AppColors.white,
                focusedTextColor = AppColors.black
            ),
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
            value = value,
            onValueChange = onValueChange
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun InputFieldPreview1() {
    InputField()
}

@Preview(showBackground = true)
@Composable
private fun InputFieldPreview2() {
    InputField(value = "InputValue")
}