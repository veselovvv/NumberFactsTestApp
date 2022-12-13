package com.veselovvv.numberfactstestapp.domain.facts

import com.veselovvv.numberfactstestapp.data.facts.FactsDataToDomainMapper
import com.veselovvv.numberfactstestapp.data.facts.FactsRepository

interface DeleteFactsUseCase {
    suspend fun execute(): FactsDomain

    class Base(
        private val repository: FactsRepository,
        private val factsMapper: FactsDataToDomainMapper
    ) : DeleteFactsUseCase {
        override suspend fun execute() = repository.deleteFacts().map(factsMapper)
    }
}