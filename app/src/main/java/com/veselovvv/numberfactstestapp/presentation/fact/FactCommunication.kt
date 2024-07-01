package com.veselovvv.numberfactstestapp.presentation.fact

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface FactCommunication {
    fun getLiveData(): LiveData<FactElementUi>
    fun map(fact: FactElementUi)
    fun observe(owner: LifecycleOwner, observer: Observer<FactElementUi>) // todo delete

    class Base : FactCommunication {
        private val liveData = MutableLiveData<FactElementUi>()

        override fun getLiveData() = liveData

        override fun map(fact: FactElementUi) {
            liveData.value = fact
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<FactElementUi>) =
            liveData.observe(owner, observer)
    }
}