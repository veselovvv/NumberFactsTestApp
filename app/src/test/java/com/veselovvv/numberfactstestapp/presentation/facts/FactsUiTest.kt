package com.veselovvv.numberfactstestapp.presentation.facts

import com.veselovvv.numberfactstestapp.domain.facts.FactDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class FactsUiTest {
    @Test
    fun test_success() {
        val facts = listOf(
            FactDomain(1, "1 is the loneliest number."),
            FactDomain(10, "10 is the number of Provinces in Canada."),
            FactDomain(99, "99 is the highest jersey number allowed in most major league sports.")
        )

        var ui = FactsUi.Success(facts, BaseFactDomainToUiMapper())
        val communication = TestFactsCommunication()
        ui.map(communication)

        var expected = listOf<FactUi>(
            FactUi.Base(1, "1 is the loneliest number."),
            FactUi.Base(10, "10 is the number of Provinces in Canada."),
            FactUi.Base(99, "99 is the highest jersey number allowed in most major league sports.")
        )
        var actual = communication.getFacts()
        assertEquals(expected, actual)

        ui = FactsUi.Success(listOf(), BaseFactDomainToUiMapper())
        ui.map(communication)

        expected = listOf(FactUi.NoHistory)
        actual = communication.getFacts()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        val ui = FactsUi.Fail(GENERIC_ERROR_MESSAGE)
        val communication = TestFactsCommunication()
        ui.map(communication)

        val expected = listOf(FactUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getFacts()
        assertEquals(expected, actual)
    }

    companion object {
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}