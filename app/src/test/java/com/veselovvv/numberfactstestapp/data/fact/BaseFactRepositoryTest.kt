package com.veselovvv.numberfactstestapp.data.fact

import com.veselovvv.numberfactstestapp.data.TestException
import com.veselovvv.numberfactstestapp.data.facts.FactDb
import com.veselovvv.numberfactstestapp.data.facts.ToFactMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseFactRepositoryTest {
    @Test
    fun test_fetch_fact_cloud_success_cache_success() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(true)
        val testCacheDataSource = TestFactCacheDataSource(true)

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactCloudMapper.Base(ToFactMapper.Base())
        )

        val expected = FactDetailsData.Success
        val actual = repository.fetchFact(1)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_fact_cloud_fail_cache_fail() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(false)
        val testCacheDataSource = TestFactCacheDataSource(false)

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactCloudMapper.Base(ToFactMapper.Base())
        )

        val expected = FactDetailsData.Fail(TestException(""))
        val actual = repository.fetchFact(1)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_fact_cloud_success_cache_fail() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(true)
        val testCacheDataSource = TestFactCacheDataSource(false)

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactCloudMapper.Base(ToFactMapper.Base())
        )

        val expected = FactDetailsData.Fail(TestException(""))
        val actual = repository.fetchFact(1)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_fact_cloud_fail_cache_success() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(false)
        val testCacheDataSource = TestFactCacheDataSource(true)

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactCloudMapper.Base(ToFactMapper.Base())
        )

        val expected = FactDetailsData.Fail(TestException(""))
        val actual = repository.fetchFact(1)
        assertEquals(expected, actual)
    }

    class TestFactCloudDataSource(private val success: Boolean) : FactCloudDataSource {
        override fun fetchFact(number: Int) =
            if (success) FactCloud("1 is the loneliest number.")
            else throw TestException("")
    }

    class TestFactCacheDataSource(private val success: Boolean) : FactCacheDataSource {
        override fun saveFact(fact: FactDb) = if (success) Unit else throw TestException("")
    }
}