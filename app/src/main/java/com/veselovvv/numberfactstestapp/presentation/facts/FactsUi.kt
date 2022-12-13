package com.veselovvv.numberfactstestapp.presentation.facts

import com.veselovvv.numberfactstestapp.domain.facts.FactDomain
import com.veselovvv.numberfactstestapp.domain.facts.FactDomainToUiMapper

sealed class FactsUi {
    abstract fun map(communication: FactsCommunication)

    data class Success(
        private val facts: List<FactDomain>,
        private val factMapper: FactDomainToUiMapper
    ) : FactsUi() {
        override fun map(communication: FactsCommunication) {
            val uiFacts = if (facts.isEmpty())
                listOf(FactUi.NoHistory)
            else facts.map { fact ->
                fact.map(factMapper)
            }

            communication.map(uiFacts)
        }
    }

    data class Fail(private val errorMessage: String) : FactsUi() {
        override fun map(communication: FactsCommunication) =
            communication.map(listOf(FactUi.Fail(errorMessage)))
    }
}
