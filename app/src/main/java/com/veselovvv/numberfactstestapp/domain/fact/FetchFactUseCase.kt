package com.veselovvv.numberfactstestapp.domain.fact

import com.veselovvv.numberfactstestapp.data.fact.FactDetailsDataToDomainMapper
import com.veselovvv.numberfactstestapp.data.fact.FactRepository

interface FetchFactUseCase {
    suspend fun execute(number: Int): FactDetailsDomain

    class Base(
        private val repository: FactRepository,
        private val mapper: FactDetailsDataToDomainMapper
    ) : FetchFactUseCase {
        override suspend fun execute(number: Int) = repository.fetchFact(number).map(mapper)
    }
}