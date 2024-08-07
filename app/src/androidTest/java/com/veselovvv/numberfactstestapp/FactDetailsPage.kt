package com.veselovvv.numberfactstestapp

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.veselovvv.numberfactstestapp.presentation.main.MainActivity

class FactDetailsPage(
    composeRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) : AbstractPage(composeRule, "FactDetailsScreen") {
    fun checkFactDetailsState(number: String, fact: String) {
        checkNodeIsDisplayedWithText(number)
        checkNodeIsDisplayedWithText(fact)
    }
}
