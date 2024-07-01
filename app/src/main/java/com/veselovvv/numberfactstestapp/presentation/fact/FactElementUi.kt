package com.veselovvv.numberfactstestapp.presentation.fact

import androidx.compose.runtime.Composable
import com.veselovvv.numberfactstestapp.presentation.core.FailScreen
import com.veselovvv.numberfactstestapp.presentation.core.ProgressScreen

sealed class FactElementUi {
    @Composable
    open fun map(onSuccess: () -> Unit) = Unit

    object Progress : FactElementUi() {
        @Composable
        override fun map(onSuccess: () -> Unit) = ProgressScreen()
    }

    object Success : FactElementUi() {
        @Composable
        override fun map(onSuccess: () -> Unit) = onSuccess()
    }

    data class Fail(private val errorMessage: String) : FactElementUi() {
        @Composable
        override fun map(onSuccess: () -> Unit) = FailScreen(errorMessage)
    }
}
