package com.veselovvv.numberfactstestapp

import com.veselovvv.numberfactstestapp.data.core.FactsDatabase
import com.veselovvv.numberfactstestapp.data.facts.FactsDataSource

class TestFactsDataSource(dataBase: FactsDatabase) : FactsDataSource {
    private val dao = dataBase.factsDao()

    override fun getFacts() =
        dao.getFacts().sortedByDescending { fact -> fact.currentDateTime }

    override fun deleteFacts() = dao.deleteFacts()
}
