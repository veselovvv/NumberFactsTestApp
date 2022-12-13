package com.veselovvv.numberfactstestapp.domain.facts

import com.veselovvv.numberfactstestapp.core.ErrorType
import com.veselovvv.numberfactstestapp.presentation.facts.FactsUi

interface FactsDomainToUiMapper {
    fun map(facts: List<FactDomain>): FactsUi
    fun map(error: ErrorType): FactsUi
}