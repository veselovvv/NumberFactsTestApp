package com.veselovvv.numberfactstestapp.presentation.facts

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class TestFactsCommunication : FactsCommunication {
    private var facts = listOf<FactUi>()

    fun getFacts() = facts

    override fun map(facts: List<FactUi>) {
        this.facts = facts
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<FactUi>>) = Unit
}