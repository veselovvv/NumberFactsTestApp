package com.veselovvv.numberfactstestapp.domain.facts

data class FactDomain(
    private val number: Int, // TODO make bigger?
    private val fact: String
) {
    fun map(mapper: FactDomainToUiMapper) = mapper.map(number, fact)
}
