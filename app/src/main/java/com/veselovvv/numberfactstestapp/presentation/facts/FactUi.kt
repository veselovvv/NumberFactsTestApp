package com.veselovvv.numberfactstestapp.presentation.facts

import androidx.compose.runtime.Composable
import com.veselovvv.numberfactstestapp.presentation.core.FailScreen
import com.veselovvv.numberfactstestapp.presentation.core.NoHistoryScreen
import com.veselovvv.numberfactstestapp.presentation.main.FactBaseView

sealed class FactUi {
    @Composable
    abstract fun map(
        onFactClick: (Int, String) -> Unit,
        onRetryButtonClick: () -> Unit
    )

    object NoHistory : FactUi() {
        @Composable
        override fun map(
            onFactClick: (Int, String) -> Unit,
            onRetryButtonClick: () -> Unit
        ) = NoHistoryScreen()
    }

    data class Base(
        private val number: Int,
        private val fact: String
    ) : FactUi() {
        @Composable
        override fun map(
            onFactClick: (Int, String) -> Unit,
            onRetryButtonClick: () -> Unit
        ) = FactBaseView(number, fact, onFactClick)
    }

    data class Fail(private val errorMessage: String) : FactUi() {
        @Composable
        override fun map(
            onFactClick: (Int, String) -> Unit,
            onRetryButtonClick: () -> Unit
        ) = FailScreen(errorMessage, onRetryButtonClick)
    }
}
