package com.veselovvv.numberfactstestapp.presentation.fact

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface FactCommunication {
    fun map(fact: FactElementUi)
    fun observe(owner: LifecycleOwner, observer: Observer<FactElementUi>)

    class Base : FactCommunication {
        private val liveData = MutableLiveData<FactElementUi>()

        override fun map(fact: FactElementUi) {
            liveData.value = fact
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<FactElementUi>) =
            liveData.observe(owner, observer)
    }
}