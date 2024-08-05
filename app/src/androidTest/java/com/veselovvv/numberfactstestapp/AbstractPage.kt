package com.veselovvv.numberfactstestapp

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.veselovvv.numberfactstestapp.presentation.main.MainActivity

abstract class AbstractPage(
    private val composeRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {
    fun getString(@StringRes stringId: Int) = composeRule.activity.getString(stringId)

    fun checkNodeIsDisplayedWithText(@StringRes stringId: Int) =
        checkNodeIsDisplayedWithText(getString(stringId))

    fun checkNodeIsDisplayedWithText(message: String) {
        composeRule.onNodeWithText(message).assertIsDisplayed()
    }

    fun clickOnNode(@StringRes stringId: Int) {
        composeRule.onNodeWithText(getString(stringId)).performClick()
    }
}