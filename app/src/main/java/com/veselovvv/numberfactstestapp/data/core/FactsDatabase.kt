package com.veselovvv.numberfactstestapp.data.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.veselovvv.numberfactstestapp.data.facts.FactDb
import com.veselovvv.numberfactstestapp.data.facts.FactsDao

@Database(entities = [ FactDb::class ], version = 1)
abstract class FactsDatabase : RoomDatabase() {
    abstract fun factsDao(): FactsDao
}