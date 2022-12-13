package com.veselovvv.numberfactstestapp.domain.facts

import com.veselovvv.numberfactstestapp.core.ErrorType
import com.veselovvv.numberfactstestapp.data.facts.FactData
import com.veselovvv.numberfactstestapp.data.facts.FactDataToDomainMapper
import com.veselovvv.numberfactstestapp.presentation.facts.FactsUi

sealed class FactsDomain {
    abstract fun map(factsMapper: FactsDomainToUiMapper): FactsUi

    data class Success(
        private val facts: List<FactData>,
        private val factMapper: FactDataToDomainMapper
    ) : FactsDomain() {
        override fun map(factsMapper: FactsDomainToUiMapper) = factsMapper.map(
            facts.map { fact ->
                fact.map(factMapper)
            }
        )
    }

    data class Fail(private val error: ErrorType) : FactsDomain() {
        override fun map(factsMapper: FactsDomainToUiMapper) = factsMapper.map(error)
    }
}
