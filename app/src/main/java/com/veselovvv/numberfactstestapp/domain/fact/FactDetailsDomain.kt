package com.veselovvv.numberfactstestapp.domain.fact

import com.veselovvv.numberfactstestapp.core.ErrorType
import com.veselovvv.numberfactstestapp.presentation.fact.FactDetailsUi

sealed class FactDetailsDomain {
    abstract fun map(mapper: FactDetailsDomainToUiMapper): FactDetailsUi

    object Success : FactDetailsDomain() {
        override fun map(mapper: FactDetailsDomainToUiMapper) = mapper.map()
    }

    data class Fail(private val error: ErrorType) : FactDetailsDomain() {
        override fun map(mapper: FactDetailsDomainToUiMapper) = mapper.map(error)
    }
}
