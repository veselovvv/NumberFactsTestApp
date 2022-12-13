package com.veselovvv.numberfactstestapp.domain.facts

import com.veselovvv.numberfactstestapp.data.facts.FactsDataToDomainMapper
import com.veselovvv.numberfactstestapp.data.facts.FactsRepository

interface FetchFactsUseCase {
    suspend fun execute(): FactsDomain

    class Base(
        private val repository: FactsRepository,
        private val factsMapper: FactsDataToDomainMapper
    ) : FetchFactsUseCase {
        override suspend fun execute() = repository.fetchFacts().map(factsMapper)
    }
}