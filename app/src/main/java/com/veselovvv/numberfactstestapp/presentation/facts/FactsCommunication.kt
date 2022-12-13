package com.veselovvv.numberfactstestapp.presentation.facts

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface FactsCommunication {
    fun map(facts: List<FactUi>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<FactUi>>)

    class Base : FactsCommunication {
        private val liveData = MutableLiveData<List<FactUi>>()

        override fun map(facts: List<FactUi>) {
            liveData.value = facts
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<FactUi>>) =
            liveData.observe(owner, observer)
    }
}