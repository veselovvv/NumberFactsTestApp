package com.veselovvv.numberfactstestapp.data.facts

import com.veselovvv.numberfactstestapp.data.TestException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseFactsRepositoryTest {
    @Test
    fun test_fetch_facts_success() = runBlocking {
        val testDataSource = TestFactsDataSource(true)
        val repository = FactsRepository.Base(testDataSource, FactsDbToDataMapper.Base(ToFactMapper.Base()))

        val expected = FactsData.Success(
            listOf(
                FactData(1, "1 is the loneliest number."),
                FactData(10, "10 is the number of Provinces in Canada."),
                FactData(99, "99 is the highest jersey number allowed in most major league sports.")
            )
        )
        val actual = repository.fetchFacts()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_facts_fail() = runBlocking {
        val testDataSource = TestFactsDataSource(false)
        val repository = FactsRepository.Base(testDataSource, FactsDbToDataMapper.Base(ToFactMapper.Base()))

        val expected = FactsData.Fail(TestException(""))
        val actual = repository.fetchFacts()
        assertEquals(expected, actual)
    }

    @Test
    fun test_delete_facts_success() = runBlocking {
        val testDataSource = TestFactsDataSource(true)
        val repository = FactsRepository.Base(testDataSource, FactsDbToDataMapper.Base(ToFactMapper.Base()))

        val expected = FactsData.Success(listOf())
        val actual = repository.deleteFacts()
        assertEquals(expected, actual)
    }

    @Test
    fun test_delete_facts_fail() = runBlocking {
        val testDataSource = TestFactsDataSource(false)
        val repository = FactsRepository.Base(testDataSource, FactsDbToDataMapper.Base(ToFactMapper.Base()))

        val expected = FactsData.Fail(TestException(""))
        val actual = repository.deleteFacts()
        assertEquals(expected, actual)
    }

    class TestFactsDataSource(private val success: Boolean) : FactsDataSource {
        // TODO add method save() here or make 2 different data sources?

        override fun getFacts() = if (success) listOf(
            FactDb(1, "1 is the loneliest number."),
            FactDb(10, "10 is the number of Provinces in Canada."),
            FactDb(99, "99 is the highest jersey number allowed in most major league sports.")
        ) else throw TestException("")

        override fun deleteFacts() = if (success) Unit else throw TestException("")
    }
}