package com.veselovvv.numberfactstestapp.presentation.facts

import com.veselovvv.numberfactstestapp.TestResourceProvider
import com.veselovvv.numberfactstestapp.core.ErrorType
import com.veselovvv.numberfactstestapp.data.facts.FactData
import com.veselovvv.numberfactstestapp.domain.facts.BaseFactDataToDomainMapper
import com.veselovvv.numberfactstestapp.domain.facts.DeleteFactsUseCase
import com.veselovvv.numberfactstestapp.domain.facts.FactsDomain
import com.veselovvv.numberfactstestapp.domain.facts.FetchFactsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test

class FactsViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_facts_success() = runBlocking {
        val communication = TestCommunication()
        val dispatchers = UnconfinedTestDispatcher()
        val deleteFactsUseCase = TestDeleteFactsUseCase()
        val factsMapper = BaseFactsDomainToUiMapper(TestResourceProvider(), BaseFactDomainToUiMapper())
        val factCache = TestFactCache()

        var viewModel = FactsViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchFactsUseCase(success = true, isListEmpty = false),
            deleteFactsUseCase,
            factsMapper,
            factCache
        )

        viewModel.fetchFacts()

        var expected = listOf<FactUi>(
            FactUi.Base(1, "1 is the loneliest number."),
            FactUi.Base(10, "10 is the number of Provinces in Canada."),
            FactUi.Base(99, "99 is the highest jersey number allowed in most major league sports.")
        )
        var actual = communication.getFacts()
        assertEquals(expected, actual)

        viewModel = FactsViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchFactsUseCase(success = true, isListEmpty = true),
            deleteFactsUseCase,
            factsMapper,
            factCache
        )

        viewModel.fetchFacts()

        expected = listOf(FactUi.NoHistory)
        actual = communication.getFacts()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_facts_fail() = runBlocking {
        val communication = TestCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = FactsViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchFactsUseCase(success = false, isListEmpty = false),
            TestDeleteFactsUseCase(),
            BaseFactsDomainToUiMapper(TestResourceProvider(), BaseFactDomainToUiMapper()),
            TestFactCache()
        )

        viewModel.fetchFacts()

        val expected = listOf(FactUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getFacts()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_delete_facts_success() = runBlocking {
        val communication = TestCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = FactsViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchFactsUseCase(),
            TestDeleteFactsUseCase(true),
            BaseFactsDomainToUiMapper(TestResourceProvider(), BaseFactDomainToUiMapper()),
            TestFactCache()
        )

        viewModel.deleteFacts()

        val expected = listOf(FactUi.NoHistory)
        val actual = communication.getFacts()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_delete_facts_fail() = runBlocking {
        val communication = TestCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = FactsViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchFactsUseCase(),
            TestDeleteFactsUseCase(false),
            BaseFactsDomainToUiMapper(TestResourceProvider(), BaseFactDomainToUiMapper()),
            TestFactCache()
        )

        viewModel.deleteFacts()

        val expected = listOf(FactUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getFacts()
        assertEquals(expected, actual)
    }

    class TestFetchFactsUseCase(
        private val success: Boolean = true,
        private val isListEmpty: Boolean = false
    ) : FetchFactsUseCase {
        override suspend fun execute() = if (success) {
            if (isListEmpty)
                FactsDomain.Success(listOf(), BaseFactDataToDomainMapper())
            else FactsDomain.Success(
                listOf(
                    FactData(1, "1 is the loneliest number."),
                    FactData(10, "10 is the number of Provinces in Canada."),
                    FactData(99, "99 is the highest jersey number allowed in most major league sports.")
                ),
                BaseFactDataToDomainMapper()
            )
        } else FactsDomain.Fail(ErrorType.GENERIC_ERROR)
    }

    class TestDeleteFactsUseCase(private val success: Boolean = true) : DeleteFactsUseCase {
        override suspend fun execute() =
            if (success) FactsDomain.Success(listOf(), BaseFactDataToDomainMapper())
            else FactsDomain.Fail(ErrorType.GENERIC_ERROR)
    }

    class TestFactCache : FactCache {
        override fun saveFactInfo(info: Pair<Int, String>) = Unit
        override fun readFactInfo() = Pair(1, "1 is the loneliest number.")
    }

    companion object {
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}