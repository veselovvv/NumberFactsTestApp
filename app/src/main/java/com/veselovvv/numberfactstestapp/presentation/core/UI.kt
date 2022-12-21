package com.veselovvv.numberfactstestapp.presentation.core

import android.view.View

interface UI {
    fun View.show() {
        visibility = View.VISIBLE
    }

    fun View.hide() {
        visibility = View.GONE
    }
}