package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup.SignUpScreen
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {

    @get:Rule
    val componentTestRule = createComposeRule()

    @Test
    fun term_condition이_클릭되어야_한다() {
        var count = 0

        componentTestRule.setContent {
            SignUpScreen(onClickTerm = {
                count++
            })
        }

        componentTestRule.onNodeWithText("Accept terms & Condition")
            .performClick()

        assertEquals(1, count)
    }

    @Test
    fun signIn_버튼이_클릭되어야_한다() {
        var count = 0

        componentTestRule.setContent {
            SignUpScreen(onClickSignIn = {
                count++
            })
        }

        componentTestRule.onNodeWithText("Sign in")
            .performClick()

        assertEquals(1, count)
    }

    @Test
    fun signUp이_클릭되어야_한다() {
        var count = 0

        componentTestRule.setContent {
            SignUpScreen(onClickSignUp = {
                count++
            })
        }

        componentTestRule.onNodeWithText("Sign Up")
            .performClick()

        assertEquals(1, count)
    }
}