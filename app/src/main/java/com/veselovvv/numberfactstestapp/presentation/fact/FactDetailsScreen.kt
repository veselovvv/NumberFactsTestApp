package com.veselovvv.numberfactstestapp.presentation.fact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veselovvv.numberfactstestapp.R

@Composable
fun FactDetailsScreen(viewModel: FactViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.number_label),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = viewModel.getNumber(),
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = stringResource(id = R.string.fact_label),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(start = 8.dp, top = 24.dp, end = 8.dp, bottom = 8.dp)
        )
        Text(
            text = viewModel.getFact(),
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(8.dp)
        )
    }
}