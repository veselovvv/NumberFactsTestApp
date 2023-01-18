package com.veselovvv.numberfactstestapp.presentation.facts

import android.content.Context

interface FactCache {
    fun saveFactInfo(info: Pair<String, String>)
    fun readFactInfo(): Pair<String, String>

    class Base(context: Context) : FactCache {
        private val sharedPreferences =
            context.getSharedPreferences(FACT_INFO_FILENAME, Context.MODE_PRIVATE)

        override fun saveFactInfo(info: Pair<String, String>) =
            sharedPreferences.edit()
                .putString(NUMBER_KEY, info.first)
                .putString(FACT_KEY, info.second)
                .apply()

        override fun readFactInfo() = Pair(
            sharedPreferences.getString(NUMBER_KEY, "") ?: "",
            sharedPreferences.getString(FACT_KEY, "") ?: ""
        )

        companion object {
            private const val FACT_INFO_FILENAME = "factInfo"
            private const val NUMBER_KEY = "numberKey"
            private const val FACT_KEY = "factKey"
        }
    }
}