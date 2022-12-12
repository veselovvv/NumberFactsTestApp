package com.veselovvv.numberfactstestapp.data.facts

import android.content.Context
import com.veselovvv.numberfactstestapp.data.core.BaseCacheDataSource

interface FactsDataSource {
    fun getFacts(): List<FactDb>
    fun deleteFacts()

    class Base(context: Context) : FactsDataSource, BaseCacheDataSource.Abstract<FactsDao>(context) {
        override fun dao() = room.factsDao()
        override fun getFacts() = dao().getFacts()
        override fun deleteFacts() = dao().deleteFacts()
    }
}