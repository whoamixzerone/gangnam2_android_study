package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class SmallButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun SmallButton_클릭_시_콜백_함수_실행이_된다() {
        // given
        var count = 0

        composeTestRule.setContent {
            SmallButton(text = "버튼") {
                count++
            }
        }

        // when
        composeTestRule.onNodeWithText("버튼")
            .performClick()

        // then
        assertEquals(1, count)
    }
}