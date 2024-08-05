package com.veselovvv.numberfactstestapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.veselovvv.numberfactstestapp.presentation.main.MainActivity

class DeleteHistoryIconUi(
    composeRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {
    private val deleteHistoryIcon = composeRule.onNodeWithContentDescription(
        composeRule.activity.getString(R.string.delete_history_content_description)
    )

    fun checkIsDisplayed() {
        deleteHistoryIcon.assertIsDisplayed()
    }

    fun clickButton() {
        deleteHistoryIcon.performClick()
    }
}
