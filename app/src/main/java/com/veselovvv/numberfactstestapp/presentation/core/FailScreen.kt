package com.veselovvv.numberfactstestapp.presentation.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun FailScreen(errorMessage: String) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.White), // todo for both themes
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = errorMessage,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}