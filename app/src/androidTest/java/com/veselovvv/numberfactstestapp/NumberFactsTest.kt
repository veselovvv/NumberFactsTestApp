package com.veselovvv.numberfactstestapp

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.veselovvv.numberfactstestapp.presentation.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NumberFactsTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    /**
     * Check initial ui state
     * 1. Click "Get fact" button
     * Check dialog window state with text "Text field must contain a number"
     * 2. Close dialog window
     * 3. Type "-./" in edit text
     * 4. Click "Get fact" button
     * Check dialog window state with text "Text field must contain a number"
     * 5. Close dialog window
     * 6. Type "32" in edit text
     * 7. Click "Get fact" button
     * Check facts list state with 1 element 32
     * 8. Recreate activity
     * Check facts list state with 1 element 32
     * 9. Type "45" in edit text
     * 10. Click "Get fact" button
     * Check facts list state with 1 element 45 and 2 element 32
     * 11. Type "32" in edit text
     * 12. Click "Get fact" button
     * Check facts list state with 1 element 32 and 2 element 45
     * 13. Recreate activity
     * Check facts list state with 1 element 32 and 2 element 45
     */
    @Test
    fun getFact() = with(MainPage(composeRule)) {
        checkInitialUiState()
        clickGetFactButton()
        checkDialogWindowState(
            message = composeRule.activity.getString(R.string.the_field_is_empty)
        )

        closeDialogWindow()
        typeInEditText(text = "-./")
        clickGetFactButton()
        checkDialogWindowState(
            message = composeRule.activity.getString(R.string.text_field_must_contain_a_number)
        )

        closeDialogWindow()
        typeInEditText(text = "32")
        clickGetFactButton()
        checkFactsListState(facts = listOf(Pair("32", "fact about 32")))

        composeRule.activityRule.scenario.recreate()
        checkFactsListState(facts = listOf(Pair("32", "fact about 32")))

        typeInEditText(text = "45")
        clickGetFactButton()
        checkFactsListState(
            facts = listOf(
                Pair("45", "fact about 45"),
                Pair("32", "fact about 32")
            )
        )

        typeInEditText(text = "32")
        clickGetFactButton()
        checkFactsListState(
            facts = listOf(
                Pair("32", "fact about 32"),
                Pair("45", "fact about 45")
            )
        )

        composeRule.activityRule.scenario.recreate()
        checkFactsListState(
            facts = listOf(
                Pair("32", "fact about 32"),
                Pair("45", "fact about 45")
            )
        )
    }

    /**
     * Check initial ui state
     * 1. Click "Get fact about random number" button
     * Check facts list state with 1 element 666
     * 2. Recreate activity
     * Check facts list state with 1 element 666
     */
    @Test
    fun getFactAboutRandomNumber() = with(MainPage(composeRule)) {
        checkInitialUiState()
        clickGetFactAboutRandomNumberButton()
        checkFactsListState(facts = listOf(Pair("666", "666 fact about random number")))
        composeRule.activityRule.scenario.recreate()
        checkFactsListState(facts = listOf(Pair("666", "666 fact about random number")))
    }
}