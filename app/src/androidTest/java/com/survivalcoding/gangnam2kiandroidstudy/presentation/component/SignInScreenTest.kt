package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class SignInScreenTest {

    @get:Rule
    val componentTestRule = createComposeRule()

    @Test
    fun forgot_password가_클릭되어야_한다() {
        var count = 0

        componentTestRule.setContent {
            SignInScreen(onClickForgotPassword = {
                count++
            })
        }

        componentTestRule.onNodeWithText("Forgot Password?")
            .performClick()

        assertEquals(1, count)
    }

    @Test
    fun signIn_버튼이_클릭되어야_한다() {
        var count = 0

        componentTestRule.setContent {
            SignInScreen(onClickSignIn = {
                count++
            })
        }

        componentTestRule.onNodeWithText("Sign In")
            .performClick()

        assertEquals(1, count)
    }

    @Test
    fun signUp이_클릭되어야_한다() {
        var count = 0

        componentTestRule.setContent {
            SignInScreen(onClickSignUp = {
                count++
            })
        }

        componentTestRule.onNodeWithText("Sign up")
            .performClick()

        assertEquals(1, count)
    }
}