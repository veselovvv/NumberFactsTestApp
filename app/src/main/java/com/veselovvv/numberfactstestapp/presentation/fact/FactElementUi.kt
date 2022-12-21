package com.veselovvv.numberfactstestapp.presentation.fact

import android.view.ViewGroup
import com.google.android.material.textview.MaterialTextView
import com.veselovvv.numberfactstestapp.presentation.core.UI

sealed class FactElementUi : UI {
    open fun map(progressLayout: ViewGroup) = progressLayout.hide()
    open fun map(onResult: () -> Unit) = Unit
    open fun map(failLayout: ViewGroup, messageTextView: MaterialTextView) = failLayout.hide()

    object Progress : FactElementUi() {
        override fun map(progressLayout: ViewGroup) = progressLayout.show()
    }

    object Success : FactElementUi() {
        override fun map(onResult: () -> Unit) = onResult.invoke()
    }

    data class Fail(private val errorMessage: String) : FactElementUi() {
        override fun map(failLayout: ViewGroup, messageTextView: MaterialTextView) {
            failLayout.show()
            messageTextView.text = errorMessage
        }
    }
}
