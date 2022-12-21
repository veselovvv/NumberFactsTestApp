package com.veselovvv.numberfactstestapp.domain.fact

import com.veselovvv.numberfactstestapp.core.ErrorType
import com.veselovvv.numberfactstestapp.presentation.fact.FactDetailsUi

interface FactDetailsDomainToUiMapper {
    fun map(): FactDetailsUi
    fun map(error: ErrorType): FactDetailsUi
}