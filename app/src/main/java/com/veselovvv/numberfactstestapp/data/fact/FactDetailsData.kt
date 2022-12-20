package com.veselovvv.numberfactstestapp.data.fact

import com.veselovvv.numberfactstestapp.domain.fact.FactDetailsDomain

sealed class FactDetailsData {
    abstract fun map(mapper: FactDetailsDataToDomainMapper): FactDetailsDomain

    object Success : FactDetailsData() {
        override fun map(mapper: FactDetailsDataToDomainMapper) = mapper.map()
    }

    data class Fail(private val exception: Exception) : FactDetailsData() {
        override fun map(mapper: FactDetailsDataToDomainMapper) = mapper.map(exception)
    }
}
