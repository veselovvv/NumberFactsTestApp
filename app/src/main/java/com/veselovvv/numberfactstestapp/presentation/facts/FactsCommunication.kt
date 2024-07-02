package com.veselovvv.numberfactstestapp.presentation.facts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface FactsCommunication {
    fun getLiveData(): LiveData<List<FactUi>>
    fun map(facts: List<FactUi>)

    class Base : FactsCommunication {
        private val liveData = MutableLiveData<List<FactUi>>()

        override fun getLiveData() = liveData

        override fun map(facts: List<FactUi>) {
            liveData.value = facts
        }
    }
}