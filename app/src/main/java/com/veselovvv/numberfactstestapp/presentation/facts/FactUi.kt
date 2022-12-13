package com.veselovvv.numberfactstestapp.presentation.facts

sealed class FactUi {
    open fun map(mapper: UIMapper) = Unit
    open fun showFact(factListener: FactsAdapter.FactListener) = Unit

    object Progress : FactUi()

    object NoHistory : FactUi()

    data class Base(
        private val number: Int,
        private val fact: String
    ) : FactUi() {
        override fun map(mapper: UIMapper) = mapper.map(number, fact)
        override fun showFact(factListener: FactsAdapter.FactListener) =
            factListener.showFact(number, fact)
    }

    data class Fail(private val errorMessage: String) : FactUi() {
        override fun map(mapper: UIMapper) = mapper.map(errorMessage)
    }

    interface UIMapper {
        fun map(number: Int, fact: String) = Unit
        fun map(errorMessage: String) = Unit
    }
}
