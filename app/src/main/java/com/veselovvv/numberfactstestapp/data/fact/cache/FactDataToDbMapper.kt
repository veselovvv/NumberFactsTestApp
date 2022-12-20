package com.veselovvv.numberfactstestapp.data.fact.cache

import com.veselovvv.numberfactstestapp.data.core.FactDb

interface FactDataToDbMapper {
    fun map(number: Int, fact: String): FactDb

    class Base : FactDataToDbMapper {
        override fun map(number: Int, fact: String) = FactDb(number, fact)
    }
}