package com.veselovvv.numberfactstestapp.presentation.facts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TestFactsCommunication : FactsCommunication {
    private var facts = listOf<FactUi>()

    fun getFacts() = facts

    override fun map(facts: List<FactUi>) {
        this.facts = facts
    }

    override fun getLiveData(): LiveData<List<FactUi>> = MutableLiveData()
}