package com.veselovvv.numberfactstestapp.presentation.fact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface FactCommunication {
    fun getLiveData(): LiveData<FactElementUi>
    fun map(fact: FactElementUi)

    class Base : FactCommunication {
        private val liveData = MutableLiveData<FactElementUi>()

        override fun getLiveData() = liveData

        override fun map(fact: FactElementUi) {
            liveData.value = fact
        }
    }
}