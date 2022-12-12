package com.veselovvv.numberfactstestapp.data.facts

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FactsDao {
    @Query("SELECT * FROM factDb")
    fun getFacts(): List<FactDb>

    @Query("DELETE FROM factDb")
    fun deleteFacts()
}