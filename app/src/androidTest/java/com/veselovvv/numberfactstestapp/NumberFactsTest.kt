package com.veselovvv.numberfactstestapp

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.Espresso.pressBack
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

    /**
     * Check initial ui state
     * 1. Type "32" in edit text
     * 2. Click "Get fact" button
     * Check facts list state with 1 element 32
     * 3. Type "45" in edit text
     * 4. Click "Get fact" button
     * Check facts list state with 1 element 45 and 2 element 32
     * 5. Click "Delete history" button
     * Check initial ui state
     * 6. Recreate activity
     * Check initial ui state
     */
    @Test
    fun deleteHistory() = with(MainPage(composeRule)) {
        checkInitialUiState()
        typeInEditText(text = "32")
        clickGetFactButton()
        checkFactsListState(facts = listOf(Pair("32", "fact about 32")))

        typeInEditText(text = "45")
        clickGetFactButton()
        checkFactsListState(
            facts = listOf(
                Pair("45", "fact about 45"),
                Pair("32", "fact about 32")
            )
        )

        clickDeleteHistoryButton()
        checkInitialUiState()

        composeRule.activityRule.scenario.recreate()
        checkInitialUiState()
    }

    /**
     * Check Main Page is visible
     * Check initial ui state
     * 1. Type "32" in edit text
     * 2. Click "Get fact" button
     * Check facts list state with 1 element 32
     * 3. Click on first item in list (index = 0)
     * Check Fact Details Page is visible
     * Check fact details state
     * 4. Recreate activity
     * Check Fact Details Page is visible
     * Check fact details state
     * 5. Press back button
     * Check Fact Details Page is not visible
     * Check Main Page is visible
     * Check facts list state with 1 element 32
     */
    @Test
    fun loadItemDetailsAndGoBack() {
        val mainPage = MainPage(composeRule)

        with(mainPage) {
            checkIsVisible()
            checkInitialUiState()
            typeInEditText(text = "32")
            clickGetFactButton()
            checkFactsListState(facts = listOf(Pair("32", "fact about 32")))
            clickOnItemInList(index = 0)
        }

        val factDetailsPage = FactDetailsPage(composeRule)

        with(factDetailsPage) {
            checkIsVisible()
            checkFactDetailsState(number = "32", fact = "fact about 32")

            composeRule.activityRule.scenario.recreate()
            checkIsVisible()
            checkFactDetailsState(number = "32", fact = "fact about 32")
        }

        pressBack()
        factDetailsPage.checkIsNotVisible()

        with(mainPage) {
            checkIsVisible()
            checkFactsListState(facts = listOf(Pair("32", "fact about 32")))
        }
    }
}