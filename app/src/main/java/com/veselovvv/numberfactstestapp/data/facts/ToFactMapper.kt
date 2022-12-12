package com.veselovvv.numberfactstestapp.data.facts

interface ToFactMapper {
    fun map(number: Int, fact: String): FactData

    class Base : ToFactMapper {
        override fun map(number: Int, fact: String) = FactData(number, fact)
    }
}