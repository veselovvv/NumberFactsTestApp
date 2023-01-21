package com.veselovvv.numberfactstestapp.domain.fact

import com.veselovvv.numberfactstestapp.data.fact.FactDetailsDataToDomainMapper
import com.veselovvv.numberfactstestapp.data.fact.FactRepository

interface FetchRandomFactUseCase {
    suspend fun execute(): FactDetailsDomain

    class Base(
        private val repository: FactRepository,
        private val mapper: FactDetailsDataToDomainMapper
    ) : FetchRandomFactUseCase {
        override suspend fun execute() = repository.fetchRandomFact().map(mapper)
    }
}