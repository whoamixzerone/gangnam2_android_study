package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performTextInput
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class PasswordInputFieldTest {

    @get:Rule
    var componentTestRule = createComposeRule()

    @Test
    fun 패스워드가_입력된_값으로_변경된다() {
        val expected = "1234"
        var password = ""

        componentTestRule.setContent {
            PasswordInputField { newPassword ->
                password = newPassword
            }
        }

        componentTestRule.onNodeWithText("Enter Password")
            .performTextInput(expected)

        assertEquals(expected, password)
    }
}