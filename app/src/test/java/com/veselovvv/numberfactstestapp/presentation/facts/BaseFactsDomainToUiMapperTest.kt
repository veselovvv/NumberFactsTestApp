package com.veselovvv.numberfactstestapp.presentation.facts

import org.junit.Assert.assertEquals
import org.junit.Test

class BaseFactsDomainToUiMapperTest {
    private val factMapper = BaseFactDomainToUiMapper()
    private val mapper = BaseFactsDomainToUiMapper(TestResourceProvider(), factMapper)

    @Test
    fun test_success() {
        val facts = listOf(
            FactDomain(1, "1 is the loneliest number."),
            FactDomain(10, "10 is the number of Provinces in Canada."),
            FactDomain(99, "99 is the highest jersey number allowed in most major league sports.")
        )

        val expected = FactsUi.Success(facts, factMapper)
        val actual = mapper.map(facts)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        val expected = FactsUi.Fail(GENERIC_ERROR_MESSAGE)
        val actual = mapper.map(ErrorType.GENERIC_ERROR)
        assertEquals(expected, actual)
    }

    companion object {
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}