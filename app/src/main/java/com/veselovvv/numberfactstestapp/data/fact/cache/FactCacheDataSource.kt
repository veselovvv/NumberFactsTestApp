package com.veselovvv.numberfactstestapp.data.fact.cache

import android.content.Context
import com.veselovvv.numberfactstestapp.data.core.BaseCacheDataSource
import com.veselovvv.numberfactstestapp.data.core.FactDb

interface FactCacheDataSource {
    fun saveFact(fact: FactDb)
    fun getFact(number: Int): FactDb?
    fun deleteFact(number: Int)

    class Base(context: Context) : FactCacheDataSource, BaseCacheDataSource.Abstract<FactDao>(context) {
        override fun dao() = room.factDao()
        override fun saveFact(fact: FactDb) = dao().addFact(fact)
        override fun getFact(number: Int) = dao().getFact(number)
        override fun deleteFact(number: Int) = dao().deleteFact(number)
    }
}