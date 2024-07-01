package com.veselovvv.numberfactstestapp.presentation.facts

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface FactsCommunication {
    fun getLiveData(): LiveData<List<FactUi>>
    fun map(facts: List<FactUi>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<FactUi>>)

    class Base : FactsCommunication {
        private val liveData = MutableLiveData<List<FactUi>>()

        override fun getLiveData() = liveData

        override fun map(facts: List<FactUi>) {
            liveData.value = facts
        }

        // todo
        override fun observe(owner: LifecycleOwner, observer: Observer<List<FactUi>>) =
            liveData.observe(owner, observer)
    }
}