package com.veselovvv.numberfactstestapp.presentation.fact

import org.junit.Assert.assertEquals
import org.junit.Test

class FactDetailsUiTest {
    @Test
    fun test_success() {
        val ui = FactDetailsUi.Success
        val communication = TestFactCommunication()
        ui.map(communication)

        val expected = FactElementUi.Success
        val actual = communication.getFact()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var ui = FactDetailsUi.Fail(NO_CONNECTION_MESSAGE)
        var communication = TestFactCommunication()
        ui.map(communication)

        var expected = FactElementUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = communication.getFact()
        assertEquals(expected, actual)

        ui = FactDetailsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        communication = TestFactCommunication()
        ui.map(communication)

        expected = FactElementUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = communication.getFact()
        assertEquals(expected, actual)

        ui = FactDetailsUi.Fail(GENERIC_ERROR_MESSAGE)
        communication = TestFactCommunication()
        ui.map(communication)

        expected = FactElementUi.Fail(GENERIC_ERROR_MESSAGE)
        actual = communication.getFact()
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}