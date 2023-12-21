package com.okta.capstonetestapp.ui.screen.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.okta.capstonetestapp.R
import com.okta.capstonetestapp.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KprForm(
    navController: NavHostController = rememberNavController(),
) {
    val viewModel: KprFormViewModel = viewModel()
    val kprResponse by viewModel.kprResponse.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var inputHarga by remember { mutableStateOf("") }
    var inputSukuBunga by remember { mutableStateOf("") }
    var inputUangMuka by remember { mutableStateOf("") }
    var inputJangkaWaktu by remember { mutableStateOf("") }

    var isSukuBungaTextTapped by remember { mutableStateOf(false) }
    val isSukuBungaValid =
        (inputSukuBunga.toDoubleOrNull() ?: 0.0) > 0 && (inputSukuBunga.toDoubleOrNull()
            ?: 0.0) <= 100

    var isJangkaWaktuTextTapped by remember { mutableStateOf(false) }
    val isJangkaWaktuValid =
        (inputJangkaWaktu.toDoubleOrNull() ?: 0.0) > 0 && (inputJangkaWaktu.toDoubleOrNull()
            ?: 0.0) <= 20

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.count_kpr_interest)) },
                navigationIcon = {},
                actions = {
                    // Add action icons here
                    IconButton(onClick = {
                        navController.navigate(Screen.About.route) {
                            popUpTo(Screen.PerkiraanForm.route) { saveState = true }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }) {
                        Icon(Icons.Filled.Info, contentDescription = stringResource(R.string.about))
                    }
                    IconButton(onClick = {
                        navController.navigate(Screen.Profile.route) {
                            popUpTo(Screen.PerkiraanForm.route) { saveState = true }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = stringResource(R.string.profile)
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary)
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()){
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(R.string.house_price),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                )
                OutlinedTextField(
                    value = inputHarga,
                    onValueChange = { newInput ->
                        inputHarga = newInput
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    leadingIcon = {
                        Icon(
                            painterResource(R.drawable.rupiah),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Text(
                    text = stringResource(R.string.interest_rate),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                )
                sukuBungaTextField(
                    input = inputSukuBunga,
                    onValueChange = { newInput ->
                        inputSukuBunga = newInput
                        isSukuBungaTextTapped = true
                    },
                    isError = !isSukuBungaValid,
                    isTapped = isSukuBungaTextTapped,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Text(
                    text = stringResource(R.string.down_payment),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                )
                OutlinedTextField(
                    value = inputUangMuka,
                    onValueChange = { newInput ->
                        inputUangMuka = newInput
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Text(
                    text = stringResource(R.string.time_period_year),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                )
                jangkaWaktuTextField(
                    input = inputJangkaWaktu,
                    onValueChange = { newInput ->
                        inputJangkaWaktu = newInput
                        isJangkaWaktuTextTapped = true
                    },
                    isError = !isJangkaWaktuValid,
                    isTapped = isJangkaWaktuTextTapped,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                val isButtonEnabled =
                    inputHarga.isNotEmpty() && inputSukuBunga.isNotEmpty() && inputUangMuka.isNotEmpty() && inputJangkaWaktu.isNotEmpty()
                val isInputValid = inputHarga.toDoubleOrNull()
                    ?.let { it >= 0 } == true && isJangkaWaktuValid && isSukuBungaValid && inputUangMuka.toDoubleOrNull()
                    ?.let { it >= 0 } == true
                Row(Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)) {
                    Button(
                        onClick = {
                            viewModel.kpr(
                                inputHarga = inputHarga,
                                inputSukuBunga = inputSukuBunga,
                                inputUangMuka = inputUangMuka,
                                inputJangkaWaktu = inputJangkaWaktu
                            )
                        },
                        enabled = isButtonEnabled && isInputValid,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = if (!isButtonEnabled) stringResource(R.string.complete_form)
                            else if (!isInputValid) stringResource(R.string.please_match_the_criteria)
                            else if (isButtonEnabled && isInputValid) stringResource(R.string.count)
                            else stringResource(R.string.complete_form),
                            fontSize = 16.sp
                        )
                    }
                    LaunchedEffect(kprResponse) {
                        val debtsCount = kprResponse?.debts
                        val monthlyInstallmentCount = kprResponse?.monthlyInstallment
                        val totalCount = kprResponse?.total

                        if (debtsCount != null && monthlyInstallmentCount != null && totalCount != null) {
                            println("Debts Count: $debtsCount")
                            println("Monthly Installment Count: $monthlyInstallmentCount")
                            println("Total Count: $totalCount")

                            navController.navigate("KprDetail/${inputHarga}/${inputSukuBunga}/${inputUangMuka}/${inputJangkaWaktu}/${debtsCount}/${monthlyInstallmentCount}/${totalCount}") {
                                popUpTo(Screen.Home.route) { saveState = false }
                                restoreState = true
                                launchSingleTop = true
                            }
                        } else {
                            println("Prediction is null")
                        }
                    }

                }
            }

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f))
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun sukuBungaTextField(
    input: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    isTapped: Boolean,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = input,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        isError = isError && isTapped,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
    if (isError && isTapped) {
        Text(
            text = "Value must be 1 - 100",
            color = Color.Red,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 32.dp, end = 32.dp, bottom = 8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun jangkaWaktuTextField(
    input: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    isTapped: Boolean,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = input,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        isError = isError && isTapped,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
    if (isError && isTapped) {
        Text(
            text = "Value must be 1 - 20",
            color = Color.Red,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 32.dp, end = 32.dp, bottom = 8.dp)
        )
    }
}