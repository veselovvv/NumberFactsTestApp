package com.veselovvv.numberfactstestapp.presentation.fact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TestFactCommunication : FactCommunication {
    private var fact: FactElementUi = FactElementUi.Success

    fun getFact() = fact

    override fun map(fact: FactElementUi) {
        this.fact = fact
    }

    override fun getLiveData(): LiveData<FactElementUi> = MutableLiveData()
}