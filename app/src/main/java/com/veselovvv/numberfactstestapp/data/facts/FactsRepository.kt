package com.veselovvv.numberfactstestapp.data.facts

interface FactsRepository {
    suspend fun fetchFacts(): FactsData
    suspend fun deleteFacts(): FactsData

    class Base(
        private val dataSource: FactsDataSource,
        private val mapper: FactsDbToDataMapper
    ) : FactsRepository {
        override suspend fun fetchFacts() = try {
            val dbFacts = dataSource.getFacts()
            val facts = mapper.map(dbFacts)
            FactsData.Success(facts)
        } catch (e: Exception) {
            FactsData.Fail(e)
        }

        override suspend fun deleteFacts() = try {
            dataSource.deleteFacts()
            FactsData.Success(listOf())
        } catch (e: Exception) {
            FactsData.Fail(e)
        }
    }
}
