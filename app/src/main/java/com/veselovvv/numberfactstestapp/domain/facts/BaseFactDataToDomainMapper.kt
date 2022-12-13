package com.veselovvv.numberfactstestapp.domain.facts

import com.veselovvv.numberfactstestapp.data.facts.FactDataToDomainMapper

class BaseFactDataToDomainMapper : FactDataToDomainMapper {
    override fun map(number: Int, fact: String) = FactDomain(number, fact)
}