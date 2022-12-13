package com.veselovvv.numberfactstestapp.presentation.facts

import com.veselovvv.numberfactstestapp.domain.facts.FactDomainToUiMapper

class BaseFactDomainToUiMapper : FactDomainToUiMapper {
    override fun map(number: Int, fact: String) = FactUi.Base(number, fact)
}