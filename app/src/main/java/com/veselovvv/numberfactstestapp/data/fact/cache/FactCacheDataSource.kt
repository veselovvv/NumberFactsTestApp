package com.veselovvv.numberfactstestapp.data.fact.cache

import android.content.Context
import com.veselovvv.numberfactstestapp.data.core.BaseCacheDataSource
import com.veselovvv.numberfactstestapp.data.core.FactDb

interface FactCacheDataSource {
    fun saveFact(fact: FactDb)

    class Base(context: Context) : FactCacheDataSource, BaseCacheDataSource.Abstract<FactDao>(context) {
        override fun dao() = room.factDao()
        override fun saveFact(fact: FactDb) = dao().addFact(fact)
    }
}