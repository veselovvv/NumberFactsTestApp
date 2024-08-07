package com.veselovvv.numberfactstestapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.veselovvv.numberfactstestapp.presentation.main.MainActivity

class FactsListUi(
    composeRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {
    private val factsList = composeRule.onNodeWithTag("Facts List")

    fun checkInitialState(text: String) {
        with(factsList) {
            assertIsDisplayed()

            onChildren()
                .onFirst()
                .assertTextEquals(text)
        }
    }

    fun checkListState(facts: List<Pair<String, String>>) {
        facts.forEachIndexed { index, (number, fact) ->
            val factItem = factsList.onChildren()[index]

            factItem.assertTextContains(number)
            factItem.assertTextContains(fact)
        }
    }

    fun clickOnItemInList(index: Int) {
        factsList.onChildren()[index].performClick()
    }
}
