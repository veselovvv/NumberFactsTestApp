package com.veselovvv.numberfactstestapp.data.fact.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.veselovvv.numberfactstestapp.data.core.FactDb

@Dao
interface FactDao {
    @Insert
    fun addFact(fact: FactDb)

    @Query("SELECT * FROM factdb WHERE number IS :number")
    fun getFact(number: Int): FactDb?

    @Query("DELETE FROM factdb WHERE number IS :number")
    fun deleteFact(number: Int)
}