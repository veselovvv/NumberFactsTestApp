package com.veselovvv.numberfactstestapp.domain.facts

import com.veselovvv.numberfactstestapp.core.ErrorType
import com.veselovvv.numberfactstestapp.data.facts.FactData
import com.veselovvv.numberfactstestapp.data.facts.FactDataToDomainMapper
import com.veselovvv.numberfactstestapp.data.facts.FactsDataToDomainMapper

class BaseFactsDataToDomainMapper(
    private val factMapper: FactDataToDomainMapper
) : FactsDataToDomainMapper {
    override fun map(facts: List<FactData>) = FactsDomain.Success(facts, factMapper)
    override fun map(exception: Exception) = FactsDomain.Fail(ErrorType.GENERIC_ERROR)
}