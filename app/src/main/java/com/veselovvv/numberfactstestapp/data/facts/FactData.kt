package com.veselovvv.numberfactstestapp.data.facts

data class FactData(
    private val number: Int, // TODO make bigger?
    private val fact: String
) {
    fun map(mapper: FactDataToDomainMapper) = mapper.map(number, fact)
}
