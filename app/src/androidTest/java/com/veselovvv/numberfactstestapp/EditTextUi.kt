package com.veselovvv.numberfactstestapp

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.veselovvv.numberfactstestapp.presentation.main.MainActivity

class EditTextUi(
    private val composeRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {
    fun typeInEditText(hint: String, text: String) {
        val textField = composeRule.onNodeWithText(hint)

        with(textField) {
            performTextClearance()
            performTextInput(text)
        }
    }
}
