package com.veselovvv.numberfactstestapp.data.fact.cloud

interface FactCloudDataSource {
    suspend fun fetchFact(number: Int): String

    class Base(private val service: FactService) : FactCloudDataSource {
        override suspend fun fetchFact(number: Int): String = service.fetchFact(number).string()
    }
}