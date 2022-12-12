package com.veselovvv.numberfactstestapp.data.core

import android.content.Context
import androidx.room.Room

interface BaseCacheDataSource<D> {
    fun dao(): D

    abstract class Abstract<D>(context: Context) : BaseCacheDataSource<D> {
        protected val room = Room.databaseBuilder(
            context,
            FactsDatabase::class.java,
            DATABASE_NAME
        ).build()

        companion object {
            private const val DATABASE_NAME = "facts-database"
        }
    }
}