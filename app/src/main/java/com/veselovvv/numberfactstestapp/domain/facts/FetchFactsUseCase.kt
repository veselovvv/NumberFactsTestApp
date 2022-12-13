package com.veselovvv.numberfactstestapp.domain.facts

import com.veselovvv.numberfactstestapp.data.facts.FactsDataToDomainMapper
import com.veselovvv.numberfactstestapp.data.facts.FactsRepository

class FetchFactsUseCase(
    private val repository: FactsRepository,
    private val factsMapper: FactsDataToDomainMapper
) {
    suspend fun execute() = repository.fetchFacts().map(factsMapper)
}