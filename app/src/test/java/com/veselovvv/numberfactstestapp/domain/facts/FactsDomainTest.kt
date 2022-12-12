package com.veselovvv.numberfactstestapp.domain.facts

import com.veselovvv.numberfactstestapp.TestResourceProvider
import com.veselovvv.numberfactstestapp.core.ErrorType
import com.veselovvv.numberfactstestapp.data.facts.FactData
import com.veselovvv.numberfactstestapp.presentation.facts.BaseFactDomainToUiMapper
import com.veselovvv.numberfactstestapp.presentation.facts.BaseFactsDomainToUiMapper
import com.veselovvv.numberfactstestapp.presentation.facts.FactsUi
import org.junit.Assert.assertEquals
import org.junit.Test

class FactsDomainTest {
    @Test
    fun test_success() {
        val dataFacts = listOf(
            FactData(1, "1 is the loneliest number."),
            FactData(10, "10 is the number of Provinces in Canada."),
            FactData(99, "99 is the highest jersey number allowed in most major league sports.")
        )

        val domainFacts = listOf(
            FactDomain(1, "1 is the loneliest number."),
            FactDomain(10, "10 is the number of Provinces in Canada."),
            FactDomain(99, "99 is the highest jersey number allowed in most major league sports.")
        )

        val factMapper = BaseFactDomainToUiMapper()
        val domain = FactsDomain.Success(dataFacts, BaseFactDataToDomainMapper())

        val expected = FactsUi.Success(domainFacts, factMapper)
        val actual = domain.map(BaseFactsDomainToUiMapper(TestResourceProvider(), factMapper))
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        val domain = FactsDomain.Fail(ErrorType.GENERIC_ERROR)
        val expected = FactsUi.Fail(GENERIC_ERROR_MESSAGE)
        val actual = domain.map(
            BaseFactsDomainToUiMapper(TestResourceProvider(), BaseFactDomainToUiMapper())
        )
        assertEquals(expected, actual)
    }

    companion object {
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}