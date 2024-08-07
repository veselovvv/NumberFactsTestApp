package com.veselovvv.numberfactstestapp

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.veselovvv.numberfactstestapp.presentation.main.MainActivity

class MainPage(
    composeRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) : AbstractPage(composeRule, "MainScreen") {
    private val deleteHistoryIconUi = DeleteHistoryIconUi(composeRule)
    private val factsListUi = FactsListUi(composeRule)
    private val editTextUi = EditTextUi(composeRule)

    fun checkInitialUiState() {
        checkNodeIsDisplayedWithText(R.string.enter_a_number_hint)
        checkNodeIsDisplayedWithText(R.string.get_fact)
        checkNodeIsDisplayedWithText(R.string.get_fact_about_random_number)

        deleteHistoryIconUi.checkIsDisplayed()
        factsListUi.checkInitialState(text = getString(R.string.no_history))
    }

    fun clickGetFactButton() = clickOnNode(R.string.get_fact)
    fun checkDialogWindowState(message: String) = checkNodeIsDisplayedWithText(message)
    fun closeDialogWindow() = clickOnNode(R.string.ok)

    fun typeInEditText(text: String) = editTextUi.typeInEditText(
        hint = getString(R.string.enter_a_number_hint),
        text = text
    )

    fun checkFactsListState(facts: List<Pair<String, String>>) =
        factsListUi.checkListState(facts = facts)

    fun clickGetFactAboutRandomNumberButton() =
        clickOnNode(R.string.get_fact_about_random_number)

    fun clickDeleteHistoryButton() = deleteHistoryIconUi.clickButton()
    fun clickOnItemInList(index: Int) = factsListUi.clickOnItemInList(index)
}
