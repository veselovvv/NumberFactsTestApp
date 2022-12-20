package com.veselovvv.numberfactstestapp.data.fact.cache

import androidx.room.Dao
import androidx.room.Insert
import com.veselovvv.numberfactstestapp.data.core.FactDb

@Dao
interface FactDao {
    @Insert
    fun addFact(fact: FactDb)
}