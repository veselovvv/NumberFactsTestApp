package com.veselovvv.numberfactstestapp.presentation.fact

import com.veselovvv.numberfactstestapp.TestResourceProvider
import com.veselovvv.numberfactstestapp.core.ErrorType
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseFactDetailsDomainToUiMapperTest {
    private val mapper = BaseFactDetailsDomainToUiMapper(TestResourceProvider())

    @Test
    fun test_success() {
        val expected = FactDetailsUi.Success
        val actual = mapper.map()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = FactDetailsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = FactDetailsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)

        expected = FactDetailsUi.Fail(GENERIC_ERROR_MESSAGE)
        actual = mapper.map(ErrorType.GENERIC_ERROR)
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}