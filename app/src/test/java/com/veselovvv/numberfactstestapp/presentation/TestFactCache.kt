package com.veselovvv.numberfactstestapp.presentation

import com.veselovvv.numberfactstestapp.presentation.facts.FactCache

class TestFactCache : FactCache {
    override fun saveFactInfo(info: Pair<String, String>) = Unit
    override fun readFactInfo() = Pair("1", "1 is the loneliest number.")
}