package com.veselovvv.numberfactstestapp.presentation.core

sealed class Routes(private val route: String) {
    fun getRoute() = route

    object Main : Routes("main_screen")

    object FactDetails : Routes("fact_details_screen")
}
