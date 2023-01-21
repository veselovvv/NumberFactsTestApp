package com.veselovvv.numberfactstestapp.presentation.fact

import com.veselovvv.numberfactstestapp.TestResourceProvider
import com.veselovvv.numberfactstestapp.core.ErrorType
import com.veselovvv.numberfactstestapp.domain.fact.FactDetailsDomain
import com.veselovvv.numberfactstestapp.domain.fact.FetchFactUseCase
import com.veselovvv.numberfactstestapp.domain.fact.FetchRandomFactUseCase
import com.veselovvv.numberfactstestapp.presentation.TestFactCache
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test

class FactViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_fact_success() = runBlocking {
        val communication = TestFactCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = FactViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchFactUseCase(),
            TestFetchRandomFactUseCase(),
            BaseFactDetailsDomainToUiMapper(TestResourceProvider()),
            TestFactCache()
        )

        viewModel.fetchFact(1)

        val expected = FactElementUi.Success
        val actual = communication.getFact()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_fact_fail() = runBlocking {
        var communication = TestFactCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        var viewModel = FactViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchFactUseCase(ErrorType.NO_CONNECTION),
            TestFetchRandomFactUseCase(),
            BaseFactDetailsDomainToUiMapper(TestResourceProvider()),
            TestFactCache()
        )

        viewModel.fetchFact(1)

        var expected = FactElementUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = communication.getFact()
        assertEquals(expected, actual)

        communication = TestFactCommunication()

        viewModel = FactViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchFactUseCase(ErrorType.SERVICE_UNAVAILABLE),
            TestFetchRandomFactUseCase(),
            BaseFactDetailsDomainToUiMapper(TestResourceProvider()),
            TestFactCache()
        )

        viewModel.fetchFact(1)

        expected = FactElementUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = communication.getFact()
        assertEquals(expected, actual)

        communication = TestFactCommunication()

        viewModel = FactViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchFactUseCase(ErrorType.GENERIC_ERROR),
            TestFetchRandomFactUseCase(),
            BaseFactDetailsDomainToUiMapper(TestResourceProvider()),
            TestFactCache()
        )

        viewModel.fetchFact(1)

        expected = FactElementUi.Fail(GENERIC_ERROR_MESSAGE)
        actual = communication.getFact()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_random_fact_success() = runBlocking {
        val communication = TestFactCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = FactViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchFactUseCase(),
            TestFetchRandomFactUseCase(),
            BaseFactDetailsDomainToUiMapper(TestResourceProvider()),
            TestFactCache()
        )

        viewModel.fetchRandomFact()

        val expected = FactElementUi.Success
        val actual = communication.getFact()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_random_fact_fail() = runBlocking {
        var communication = TestFactCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        var viewModel = FactViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchFactUseCase(),
            TestFetchRandomFactUseCase(ErrorType.NO_CONNECTION),
            BaseFactDetailsDomainToUiMapper(TestResourceProvider()),
            TestFactCache()
        )

        viewModel.fetchRandomFact()

        var expected = FactElementUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = communication.getFact()
        assertEquals(expected, actual)

        communication = TestFactCommunication()

        viewModel = FactViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchFactUseCase(),
            TestFetchRandomFactUseCase(ErrorType.SERVICE_UNAVAILABLE),
            BaseFactDetailsDomainToUiMapper(TestResourceProvider()),
            TestFactCache()
        )

        viewModel.fetchRandomFact()

        expected = FactElementUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = communication.getFact()
        assertEquals(expected, actual)

        communication = TestFactCommunication()

        viewModel = FactViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchFactUseCase(),
            TestFetchRandomFactUseCase(ErrorType.GENERIC_ERROR),
            BaseFactDetailsDomainToUiMapper(TestResourceProvider()),
            TestFactCache()
        )

        viewModel.fetchRandomFact()

        expected = FactElementUi.Fail(GENERIC_ERROR_MESSAGE)
        actual = communication.getFact()
        assertEquals(expected, actual)
    }

    class TestFetchFactUseCase(private val error: ErrorType? = null) : FetchFactUseCase {
        override suspend fun execute(number: Int) =
            if (error == null) FactDetailsDomain.Success
            else FactDetailsDomain.Fail(error)
    }

    class TestFetchRandomFactUseCase(private val error: ErrorType? = null) : FetchRandomFactUseCase {
        override suspend fun execute() =
            if (error == null) FactDetailsDomain.Success
            else FactDetailsDomain.Fail(error)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}