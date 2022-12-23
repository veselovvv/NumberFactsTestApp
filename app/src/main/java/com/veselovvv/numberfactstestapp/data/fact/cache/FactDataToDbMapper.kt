package com.veselovvv.numberfactstestapp.data.fact.cache

import com.veselovvv.numberfactstestapp.data.core.FactDb
import java.text.SimpleDateFormat
import java.util.*

interface FactDataToDbMapper {
    fun map(number: Int, fact: String): FactDb

    class Base : FactDataToDbMapper {
        override fun map(number: Int, fact: String): FactDb {
            val currentDateTime: String =
                SimpleDateFormat(DATE_TIME_PATTERN, Locale.getDefault()).format(Date())
            return FactDb(number, fact, currentDateTime)
        }

        companion object {
            private const val DATE_TIME_PATTERN = "yyyyMMddHHmmss"
        }
    }
}