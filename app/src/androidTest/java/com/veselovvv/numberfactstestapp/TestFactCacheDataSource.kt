package com.veselovvv.numberfactstestapp

import com.veselovvv.numberfactstestapp.data.core.FactDb
import com.veselovvv.numberfactstestapp.data.core.FactsDatabase
import com.veselovvv.numberfactstestapp.data.fact.cache.FactCacheDataSource

class TestFactCacheDataSource(database: FactsDatabase) : FactCacheDataSource {
    private val dao = database.factDao()

    override fun saveFact(fact: FactDb) = dao.addFact(fact)
    override fun getFact(number: Int) = dao.getFact(number)
    override fun deleteFact(number: Int) = dao.deleteFact(number)
}
