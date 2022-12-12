package com.veselovvv.numberfactstestapp.data.facts

import com.veselovvv.numberfactstestapp.domain.facts.FactsDomain

interface FactsDataToDomainMapper {
    fun map(facts: List<FactData>): FactsDomain
    fun map(exception: Exception): FactsDomain
}