package com.veselovvv.numberfactstestapp.domain.facts

data class FactDomain(
    private val number: Int,
    private val fact: String
) {
    fun map(mapper: FactDomainToUiMapper) = mapper.map(number, fact)
}
