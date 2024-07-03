package com.veselovvv.numberfactstestapp.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.veselovvv.numberfactstestapp.R
import com.veselovvv.numberfactstestapp.presentation.core.Routes
import com.veselovvv.numberfactstestapp.presentation.fact.FactViewModel
import com.veselovvv.numberfactstestapp.presentation.facts.FactUi
import com.veselovvv.numberfactstestapp.presentation.facts.FactsViewModel

@Composable
fun MainScreen(
    factsViewModel: FactsViewModel,
    factViewModel: FactViewModel,
    navController: NavController
) {
    val factsUiState = factsViewModel.getFactsLiveData().observeAsState(emptyList())
    val factElementUiState = factViewModel.getFactElementUiLiveData().observeAsState()

    var textFieldState by remember {
        mutableStateOf("")
    }

    var openAlertDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) { // code in this block runs only once since key1 = true
        factsViewModel.fetchFacts()
    }

    if (openAlertDialog) {
        AlertDialog(
            title = stringResource(id = R.string.text_field_must_contain_a_number),
            onDismiss = {
                openAlertDialog = false
            },
            onConfirm = {
                openAlertDialog = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        EnterNumberTextField(textFieldState = textFieldState) { text ->
            textFieldState = text
        }
        GetFactButton(textFieldState) {
            if (textFieldState.isDigitsOnly())
                factViewModel.fetchFact(textFieldState.toInt())
            else
                openAlertDialog = true

        }
        GetFactAboutRandomNumberButton {
            factViewModel.fetchRandomFact()
        }
        HistoryLabelAndDeleteIconSection {
            factsViewModel.deleteFacts()
        }
        FactsList(
            facts = factsUiState.value,
            onFactClick = { number, fact ->
                factsViewModel.saveFactInfo(number.toString(), fact)
                navController.navigate(Routes.FactDetails.getRoute())
            },
            onRetryButtonClick = {
                factsViewModel.fetchFacts()
            }
        )
    }

    factElementUiState.value?.map(
        onSuccess = {
            factsViewModel.fetchFacts() // reload list of facts from database if success
        },
        onRetryButtonClick = {
            factsViewModel.fetchFacts()
        }
    )
}

@Composable
fun EnterNumberTextField(textFieldState: String, onTextFieldValueChange: (String) -> Unit) {
    TextField(
        value = textFieldState,
        label = {
            Text(text = stringResource(id = R.string.enter_a_number_hint))
        },
        onValueChange = { text ->
            onTextFieldValueChange(text)
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun GetFactButton(textFieldState: String, onButtonClick: () -> Unit) {
    var openAlertDialog by remember {
        mutableStateOf(false)
    }

    if (openAlertDialog) {
        AlertDialog(
            title = stringResource(id = R.string.the_field_is_empty),
            onDismiss = {
                openAlertDialog = false
            },
            onConfirm = {
                openAlertDialog = false
            }
        )
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = {
            if (textFieldState.isEmpty())
                openAlertDialog = true
            else onButtonClick()
        },
        colors = ButtonColors( // TODO change to Compose colors + check in different themes
            containerColor = Color(0xFFFF9800),
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.LightGray
        )
    ) {
        Text(text = stringResource(id = R.string.get_fact))
    }
}

@Composable
fun AlertDialog(
    title: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        shape = RoundedCornerShape(15.dp),
        title = {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.please_enter_a_number),
                fontSize = 18.sp
            )
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(
                    text = stringResource(id = R.string.ok),
                    fontSize = 18.sp
                )
            }
        }
    )
}

@Composable
fun GetFactAboutRandomNumberButton(onButtonClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onButtonClick,
        colors = ButtonColors( // TODO change to Compose colors + check in different themes
            containerColor = Color(0xFFFF9800),
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.LightGray
        )
    ) {
        Text(text = stringResource(id = R.string.get_fact_about_random_number))
    }
}

@Composable
fun HistoryLabelAndDeleteIconSection(onDeleteHistoryIconClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.history),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_delete_24),
            contentDescription = stringResource(R.string.delete_history_content_description),
            tint = Color.Red,
            modifier = Modifier.clickable {
                onDeleteHistoryIconClick()
            }
        )
    }
}

@Composable
fun FactsList(
    facts: List<FactUi>,
    onFactClick: (Int, String) -> Unit,
    onRetryButtonClick: () -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(facts) { factUI ->
            factUI.map(
                onFactClick = { number, fact ->
                    onFactClick(number, fact)
                },
                onRetryButtonClick = onRetryButtonClick
            )
        }
    }
}

@Composable
fun FactBaseView(number: Int, fact: String, onFactClick: (Int, String) -> Unit) {
    Column(modifier = Modifier.clickable {
        onFactClick(number, fact)
    }) {
        Text(
            text = number.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = fact,
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}