package com.veselovvv.numberfactstestapp

import com.veselovvv.numberfactstestapp.data.fact.cloud.FactCloudDataSource

class TestFactCloudDataSource : FactCloudDataSource {
    override suspend fun fetchFact(number: Int) = "fact about $number"
    override suspend fun fetchRandomFact() = "666 fact about random number"
}
