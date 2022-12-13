package com.veselovvv.numberfactstestapp.presentation.facts

import com.veselovvv.numberfactstestapp.R
import com.veselovvv.numberfactstestapp.core.ErrorType
import com.veselovvv.numberfactstestapp.core.ResourceProvider
import com.veselovvv.numberfactstestapp.domain.facts.FactDomain
import com.veselovvv.numberfactstestapp.domain.facts.FactDomainToUiMapper
import com.veselovvv.numberfactstestapp.domain.facts.FactsDomainToUiMapper

class BaseFactsDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val factMapper: FactDomainToUiMapper
) : FactsDomainToUiMapper {
    override fun map(facts: List<FactDomain>) = FactsUi.Success(facts, factMapper)
    override fun map(error: ErrorType) = FactsUi.Fail(
        resourceProvider.getString(R.string.something_went_wrong_message)
    )
}