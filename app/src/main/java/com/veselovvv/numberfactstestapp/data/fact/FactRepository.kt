package com.veselovvv.numberfactstestapp.data.fact

import com.veselovvv.numberfactstestapp.data.fact.cache.FactCacheDataSource
import com.veselovvv.numberfactstestapp.data.fact.cache.FactDataToDbMapper
import com.veselovvv.numberfactstestapp.data.fact.cloud.FactCloudDataSource

interface FactRepository {
    suspend fun fetchFact(number: Int): FactDetailsData

    class Base(
        private val cloudDataSource: FactCloudDataSource,
        private val cacheDataSource: FactCacheDataSource,
        private val factMapper: FactDataToDbMapper
    ) : FactRepository {
        override suspend fun fetchFact(number: Int) = try {
            val fact = cloudDataSource.fetchFact(number)
            val factDb = factMapper.map(number, fact)
            cacheDataSource.saveFact(factDb)
            FactDetailsData.Success
        } catch (e: Exception) {
            FactDetailsData.Fail(e)
        }
    }
}