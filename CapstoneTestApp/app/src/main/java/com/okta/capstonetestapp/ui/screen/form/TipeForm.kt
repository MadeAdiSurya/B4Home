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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.DateRange
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.okta.capstonetestapp.R
import com.okta.capstonetestapp.navigation.Screen
import com.okta.capstonetestapp.ui.components.MonthYearPicker


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipeForm(
    navController: NavHostController = rememberNavController(),
) {
    val viewModel: TipeFormViewModel = viewModel()
    val isLoading by viewModel.isLoading.collectAsState()
    val areaResponse by viewModel.areaResponse.collectAsState()

    var inputHarga by remember { mutableStateOf("") }

    var selectedYear by remember { mutableStateOf(2024) }
    var showDialog by remember { mutableStateOf(false) }

    var isPriceTextTapped by remember { mutableStateOf(false) }
    val isPriceValid = (inputHarga.toDoubleOrNull() ?: 0.0) >= 1000000000 && (inputHarga.toDoubleOrNull() ?: 0.0) <= 100000000000
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.find_house_preference)) },
                navigationIcon = {},
                actions = {
                    // Add action icons here
                    IconButton(onClick = {
                        navController.navigate(Screen.About.route) {
                            popUpTo(Screen.TipeForm.route) { saveState = true }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }) {
                        Icon(Icons.Filled.Info, contentDescription = stringResource(R.string.about))
                    }
                    IconButton(onClick = {
                        navController.navigate(Screen.Profile.route) {
                            popUpTo(Screen.TipeForm.route) { saveState = true }
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
                priceTextField(
                    input = inputHarga,
                    onValueChange = { newInput ->
                        inputHarga = newInput
                        isPriceTextTapped = true
                    },
                    isError = !isPriceValid,
                    isTapped = isPriceTextTapped,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Text(
                    text = stringResource(R.string.target_to_buy_a_house),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                )
                Row {
                    OutlinedTextField(
                        value = selectedYear.toString(),
                        onValueChange = {},
                        enabled = false,
                        leadingIcon = { Icon(Icons.Outlined.DateRange, contentDescription = null) },
                        modifier = Modifier
                            .weight(0.75f)
                            .padding(start = 16.dp, end = 8.dp)
                    )
                    Button(
                        onClick = { showDialog = true },
                        modifier = Modifier
                            .padding(start = 8.dp, end = 16.dp)
                            .weight(0.25f)
                            .size(56.dp)
                            .clip(CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .size(14.dp)
                        )
                    }
                    if (showDialog) {
                        Dialog(onDismissRequest = { showDialog = false }) {
                            Box(
                                modifier = Modifier
                                    .background(
                                        MaterialTheme.colorScheme.background,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .padding(16.dp)
                            ) {
                                MonthYearPicker(
                                    selectedYear = selectedYear,
                                    onYearSelected = { year ->
                                        selectedYear = year
                                        // Do something with the selected year
                                    }
                                )
                            }
                        }
                    }
                }
                val isButtonEnabled = inputHarga.isNotEmpty()
                val isInputValid = inputHarga.toDoubleOrNull()?.let { it >= 0 } == true
                Row(Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)) {
                    Button(
                        onClick = {
                            viewModel.countArea(
                                inputHarga = inputHarga.toLong(),
                                selectedYear = selectedYear
                            )
                        },
                        enabled = isButtonEnabled && isInputValid && isPriceValid,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = if (!isButtonEnabled) stringResource(R.string.complete_form)
                            else if (!isInputValid) stringResource(R.string.cant_negative)
                            else if (isButtonEnabled && isInputValid && isPriceValid) stringResource(
                                R.string.predict
                            )
                            else stringResource(R.string.complete_form),
                            fontSize = 16.sp
                        )
                    }
                    LaunchedEffect(areaResponse) {
                        val lbPrediction = areaResponse?.lb
                        val ltPrediction = areaResponse?.lt
                        if (lbPrediction != null && ltPrediction != null) {
                            println("Building Area Prediction: $lbPrediction")
                            println("Land Area Prediction: $ltPrediction")

                            navController.navigate("TipeDetail/${inputHarga}/${selectedYear}/${lbPrediction}/${ltPrediction}") {
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
fun priceTextField(
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
        leadingIcon = {
            Icon(
                painterResource(R.drawable.rupiah),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },
        isError = isError && isTapped,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
    if (isError && isTapped) {
        Text(
            text = "Minimum price should be Rp 1.000.000.000 and Max price is Rp 100.000.000.000",
            color = Color.Red,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 32.dp, end = 32.dp, bottom = 8.dp)
        )
    }
}
