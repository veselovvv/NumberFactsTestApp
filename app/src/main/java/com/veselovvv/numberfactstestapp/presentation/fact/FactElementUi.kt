package com.veselovvv.numberfactstestapp.presentation.fact

import androidx.compose.runtime.Composable
import com.veselovvv.numberfactstestapp.presentation.core.FailScreen
import com.veselovvv.numberfactstestapp.presentation.core.ProgressScreen

sealed class FactElementUi {
    @Composable
    open fun map(
        onSuccess: () -> Unit,
        onRetryButtonClick: () -> Unit
    ) = Unit

    object Progress : FactElementUi() {
        @Composable
        override fun map(
            onSuccess: () -> Unit,
            onRetryButtonClick: () -> Unit
        ) = ProgressScreen()
    }

    object Success : FactElementUi() {
        @Composable
        override fun map(
            onSuccess: () -> Unit,
            onRetryButtonClick: () -> Unit
        ) = onSuccess()
    }

    data class Fail(private val errorMessage: String) : FactElementUi() {
        @Composable
        override fun map(
            onSuccess: () -> Unit,
            onRetryButtonClick: () -> Unit
        ) = FailScreen(errorMessage, onRetryButtonClick)
    }
}
