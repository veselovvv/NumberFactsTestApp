package com.veselovvv.numberfactstestapp.data.facts

import com.veselovvv.numberfactstestapp.domain.facts.FactDomain

interface FactDataToDomainMapper {
    fun map(number: Int, fact: String): FactDomain
}