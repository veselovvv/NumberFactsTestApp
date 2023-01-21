package com.veselovvv.numberfactstestapp.data.fact

import com.veselovvv.numberfactstestapp.data.TestException
import com.veselovvv.numberfactstestapp.data.core.FactDb
import com.veselovvv.numberfactstestapp.data.fact.cache.FactCacheDataSource
import com.veselovvv.numberfactstestapp.data.fact.cache.FactDataToDbMapper
import com.veselovvv.numberfactstestapp.data.fact.cloud.FactCloudDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseFactRepositoryTest {
    @Test
    fun test_fetch_fact_cloud_success_cache_success_fact_already_exists() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(true)
        val testCacheDataSource = TestFactCacheDataSource(
            success = true,
            isFactAlreadyExists = true
        )

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactDataToDbMapper.Base()
        )

        val factBeforeFetching = testCacheDataSource.getFact(1)
        assertEquals(false, factBeforeFetching == null)

        val expected = FactDetailsData.Success
        val actual = repository.fetchFact(1)
        assertEquals(expected, actual)

        val factAfterFetching = testCacheDataSource.getFact(1)
        assertEquals(false, factAfterFetching == null)
    }

    @Test
    fun test_fetch_fact_cloud_success_cache_success_fact_doesnt_exists() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(true)
        val testCacheDataSource = TestFactCacheDataSource(
            success = true,
            isFactAlreadyExists = false
        )

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactDataToDbMapper.Base()
        )

        val factBeforeFetching = testCacheDataSource.getFact(1)
        assertEquals(true, factBeforeFetching == null)

        val expected = FactDetailsData.Success
        val actual = repository.fetchFact(1)
        assertEquals(expected, actual)

        val factAfterFetching = testCacheDataSource.getFact(1)
        assertEquals(false, factAfterFetching == null)
    }

    @Test
    fun test_fetch_fact_cloud_fail_cache_fail_fact_already_exists() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(false)
        val testCacheDataSource = TestFactCacheDataSource(
            success = false,
            isFactAlreadyExists = true
        )

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactDataToDbMapper.Base()
        )

        val factBeforeFetching = testCacheDataSource.getFact(1)
        assertEquals(false, factBeforeFetching == null)

        val expected = FactDetailsData.Fail(TestException(""))
        val actual = repository.fetchFact(1)
        assertEquals(expected, actual)

        val factAfterFetching = testCacheDataSource.getFact(1)
        assertEquals(false, factAfterFetching == null)
    }

    @Test
    fun test_fetch_fact_cloud_fail_cache_fail_fact_doesnt_exists() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(false)
        val testCacheDataSource = TestFactCacheDataSource(
            success = false,
            isFactAlreadyExists = false
        )

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactDataToDbMapper.Base()
        )

        val factBeforeFetching = testCacheDataSource.getFact(1)
        assertEquals(true, factBeforeFetching == null)

        val expected = FactDetailsData.Fail(TestException(""))
        val actual = repository.fetchFact(1)
        assertEquals(expected, actual)

        val factAfterFetching = testCacheDataSource.getFact(1)
        assertEquals(true, factAfterFetching == null)
    }

    @Test
    fun test_fetch_fact_cloud_success_cache_fail() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(true)
        val testCacheDataSource = TestFactCacheDataSource(
            success = false,
            isFactAlreadyExists = false
        )

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactDataToDbMapper.Base()
        )

        val expected = FactDetailsData.Fail(TestException(""))
        val actual = repository.fetchFact(1)
        assertEquals(expected, actual)

    }

    @Test
    fun test_fetch_fact_cloud_fail_cache_success_fact_already_exists() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(false)
        val testCacheDataSource = TestFactCacheDataSource(
            success = true,
            isFactAlreadyExists = true
        )

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactDataToDbMapper.Base()
        )

        val factBeforeFetching = testCacheDataSource.getFact(1)
        assertEquals(false, factBeforeFetching == null)

        val expected = FactDetailsData.Fail(TestException(""))
        val actual = repository.fetchFact(1)
        assertEquals(expected, actual)

        val factAfterFetching = testCacheDataSource.getFact(1)
        assertEquals(false, factAfterFetching == null)
    }

    @Test
    fun test_fetch_fact_cloud_fail_cache_success_fact_doesnt_exists() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(false)
        val testCacheDataSource = TestFactCacheDataSource(
            success = true,
            isFactAlreadyExists = false
        )

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactDataToDbMapper.Base()
        )

        val factBeforeFetching = testCacheDataSource.getFact(1)
        assertEquals(true, factBeforeFetching == null)

        val expected = FactDetailsData.Fail(TestException(""))
        val actual = repository.fetchFact(1)
        assertEquals(expected, actual)

        val factAfterFetching = testCacheDataSource.getFact(1)
        assertEquals(true, factAfterFetching == null)
    }

    @Test
    fun test_fetch_random_fact_cloud_success_cache_success_fact_already_exists() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(true)
        val testCacheDataSource = TestFactCacheDataSource(
            success = true,
            isFactAlreadyExists = true
        )

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactDataToDbMapper.Base()
        )

        val factBeforeFetching = testCacheDataSource.getFact(1)
        assertEquals(false, factBeforeFetching == null)

        val expected = FactDetailsData.Success
        val actual = repository.fetchRandomFact()
        assertEquals(expected, actual)

        val factAfterFetching = testCacheDataSource.getFact(1)
        assertEquals(false, factAfterFetching == null)
    }

    @Test
    fun test_fetch_random_fact_cloud_success_cache_success_fact_doesnt_exists() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(true)
        val testCacheDataSource = TestFactCacheDataSource(
            success = true,
            isFactAlreadyExists = false
        )

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactDataToDbMapper.Base()
        )

        val factBeforeFetching = testCacheDataSource.getFact(1)
        assertEquals(true, factBeforeFetching == null)

        val expected = FactDetailsData.Success
        val actual = repository.fetchRandomFact()
        assertEquals(expected, actual)

        val factAfterFetching = testCacheDataSource.getFact(1)
        assertEquals(false, factAfterFetching == null)
    }

    @Test
    fun test_fetch_random_fact_cloud_fail_cache_fail_fact_already_exists() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(false)
        val testCacheDataSource = TestFactCacheDataSource(
            success = false,
            isFactAlreadyExists = true
        )

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactDataToDbMapper.Base()
        )

        val factBeforeFetching = testCacheDataSource.getFact(1)
        assertEquals(false, factBeforeFetching == null)

        val expected = FactDetailsData.Fail(TestException(""))
        val actual = repository.fetchRandomFact()
        assertEquals(expected, actual)

        val factAfterFetching = testCacheDataSource.getFact(1)
        assertEquals(false, factAfterFetching == null)
    }

    @Test
    fun test_fetch_random_fact_cloud_fail_cache_fail_fact_doesnt_exists() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(false)
        val testCacheDataSource = TestFactCacheDataSource(
            success = false,
            isFactAlreadyExists = false
        )

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactDataToDbMapper.Base()
        )

        val factBeforeFetching = testCacheDataSource.getFact(1)
        assertEquals(true, factBeforeFetching == null)

        val expected = FactDetailsData.Fail(TestException(""))
        val actual = repository.fetchRandomFact()
        assertEquals(expected, actual)

        val factAfterFetching = testCacheDataSource.getFact(1)
        assertEquals(true, factAfterFetching == null)
    }

    @Test
    fun test_fetch_random_fact_cloud_success_cache_fail() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(true)
        val testCacheDataSource = TestFactCacheDataSource(
            success = false,
            isFactAlreadyExists = false
        )

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactDataToDbMapper.Base()
        )

        val expected = FactDetailsData.Fail(TestException(""))
        val actual = repository.fetchRandomFact()
        assertEquals(expected, actual)

    }

    @Test
    fun test_fetch_random_fact_cloud_fail_cache_success_fact_already_exists() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(false)
        val testCacheDataSource = TestFactCacheDataSource(
            success = true,
            isFactAlreadyExists = true
        )

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactDataToDbMapper.Base()
        )

        val factBeforeFetching = testCacheDataSource.getFact(1)
        assertEquals(false, factBeforeFetching == null)

        val expected = FactDetailsData.Fail(TestException(""))
        val actual = repository.fetchRandomFact()
        assertEquals(expected, actual)

        val factAfterFetching = testCacheDataSource.getFact(1)
        assertEquals(false, factAfterFetching == null)
    }

    @Test
    fun test_fetch_random_fact_cloud_fail_cache_success_fact_doesnt_exists() = runBlocking {
        val testCloudDataSource = TestFactCloudDataSource(false)
        val testCacheDataSource = TestFactCacheDataSource(
            success = true,
            isFactAlreadyExists = false
        )

        val repository = FactRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            FactDataToDbMapper.Base()
        )

        val factBeforeFetching = testCacheDataSource.getFact(1)
        assertEquals(true, factBeforeFetching == null)

        val expected = FactDetailsData.Fail(TestException(""))
        val actual = repository.fetchRandomFact()
        assertEquals(expected, actual)

        val factAfterFetching = testCacheDataSource.getFact(1)
        assertEquals(true, factAfterFetching == null)
    }

    class TestFactCloudDataSource(private val success: Boolean) : FactCloudDataSource {
        override suspend fun fetchFact(number: Int) =
            if (success) "1 is the loneliest number."
            else throw TestException("")

        override suspend fun fetchRandomFact() =
            if (success) "1 is the loneliest number."
            else throw TestException("")
    }

    class TestFactCacheDataSource(
        private val success: Boolean,
        isFactAlreadyExists: Boolean
    ) : FactCacheDataSource {
        private var isFactExists = isFactAlreadyExists

        override fun saveFact(fact: FactDb) =
            if (success) isFactExists = true else throw TestException("")

        override fun getFact(number: Int) =
            if (isFactExists)
                FactDb(1, "1 is the loneliest number.", "20230115082232")
            else null

        override fun deleteFact(number: Int) =
            if (success) isFactExists = false else throw TestException("")
    }
}