package com.veselovvv.numberfactstestapp.data.fact

import com.veselovvv.numberfactstestapp.domain.fact.FactDetailsDomain

interface FactDetailsDataToDomainMapper {
    fun map(): FactDetailsDomain
    fun map(exception: Exception): FactDetailsDomain
}