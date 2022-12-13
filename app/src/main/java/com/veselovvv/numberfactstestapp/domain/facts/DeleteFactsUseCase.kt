package com.veselovvv.numberfactstestapp.domain.facts

import com.veselovvv.numberfactstestapp.data.facts.FactsDataToDomainMapper
import com.veselovvv.numberfactstestapp.data.facts.FactsRepository

class DeleteFactsUseCase(
    private val repository: FactsRepository,
    private val factsMapper: FactsDataToDomainMapper
) {
    suspend fun execute() = repository.deleteFacts().map(factsMapper)
}