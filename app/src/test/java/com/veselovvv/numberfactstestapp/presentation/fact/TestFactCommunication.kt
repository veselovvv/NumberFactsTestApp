package com.veselovvv.numberfactstestapp.presentation.fact

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class TestFactCommunication : FactCommunication {
    private var fact: FactElementUi = FactElementUi.Success

    fun getFact() = fact

    override fun map(fact: FactElementUi) {
        this.fact = fact
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<FactElementUi>) = Unit
}