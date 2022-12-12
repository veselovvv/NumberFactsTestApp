package com.veselovvv.numberfactstestapp.data.facts

interface FactDataToDbMapper {
    fun map(number: Int, fact: String): FactDb

    class Base : FactDataToDbMapper {
        override fun map(number: Int, fact: String) = FactDb(number, fact)
    }
}