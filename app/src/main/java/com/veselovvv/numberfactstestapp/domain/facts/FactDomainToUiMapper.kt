package com.veselovvv.numberfactstestapp.domain.facts

import com.veselovvv.numberfactstestapp.presentation.facts.FactUi

interface FactDomainToUiMapper {
    fun map(number: Int, fact: String): FactUi
}