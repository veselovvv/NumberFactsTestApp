package com.veselovvv.numberfactstestapp.domain.fact

import com.veselovvv.numberfactstestapp.TestResourceProvider
import com.veselovvv.numberfactstestapp.core.ErrorType
import org.junit.Assert.assertEquals
import org.junit.Test

class FactDetailsDomainTest {
    @Test
    fun test_success() {
        val domain = FactDetailsDomain.Success
        val expected = FactDetailsUi.Success
        val actual = domain.map(BaseFactDetailsDomainToUiMapper(TestResourceProvider()))
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var domain = FactDetailsDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = FactDetailsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = domain.map(BaseFactDetailsDomainToUiMapper(TestResourceProvider()))
        assertEquals(expected, actual)

        domain = FactDetailsDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = FactDetailsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = domain.map(BaseFactDetailsDomainToUiMapper(TestResourceProvider()))
        assertEquals(expected, actual)

        domain = FactDetailsDomain.Fail(ErrorType.GENERIC_ERROR)
        expected = FactDetailsUi.Fail(GENERIC_ERROR_MESSAGE)
        actual = domain.map(BaseFactDetailsDomainToUiMapper(TestResourceProvider()))
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}