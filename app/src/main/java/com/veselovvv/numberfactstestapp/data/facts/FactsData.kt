package com.veselovvv.numberfactstestapp.data.facts

import com.veselovvv.numberfactstestapp.domain.facts.FactsDomain

sealed class FactsData {
    abstract fun map(mapper: FactsDataToDomainMapper): FactsDomain

    data class Success(private val facts: List<FactData>) : FactsData() {
        override fun map(mapper: FactsDataToDomainMapper) = mapper.map(facts)
    }

    data class Fail(private val exception: Exception) : FactsData() {
        override fun map(mapper: FactsDataToDomainMapper) = mapper.map(exception)
    }
}
