package com.veselovvv.numberfactstestapp.presentation.fact

sealed class FactDetailsUi {
    abstract fun map(communication: FactCommunication)

    object Success : FactDetailsUi() {
        override fun map(communication: FactCommunication) =
            communication.map(FactElementUi.Success)
    }

    data class Fail(private val errorMessage: String) : FactDetailsUi() {
        override fun map(communication: FactCommunication) =
            communication.map(FactElementUi.Fail(errorMessage))
    }
}
