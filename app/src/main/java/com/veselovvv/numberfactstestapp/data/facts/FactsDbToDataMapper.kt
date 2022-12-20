package com.veselovvv.numberfactstestapp.data.facts

import com.veselovvv.numberfactstestapp.data.core.FactDb

interface FactsDbToDataMapper {
    fun map(facts: List<FactDb>): List<FactData>

    class Base(private val factMapper: ToFactMapper) : FactsDbToDataMapper {
        override fun map(facts: List<FactDb>) = facts.map { fact ->
            fact.map(factMapper)
        }
    }
}