package com.okta.capstonetestapp.ui.screen.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.okta.capstonetestapp.R
import com.okta.capstonetestapp.navigation.Screen
import com.okta.capstonetestapp.ui.components.MonthYearPicker

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PerkiraanForm(
    navController: NavHostController = rememberNavController(),
) {
    val viewModel: PerkiraanFormViewModel = viewModel()
    val isLoading by viewModel.isLoading.collectAsState()
    val predictionResponse by viewModel.predictionResponse.collectAsState()

    var inputTabungan by remember { mutableStateOf("") }

    var selectedYear by remember { mutableStateOf(2024) }
    var showDialog by remember { mutableStateOf(false) }

    var inputKamarTidur by remember { mutableStateOf("") }
    var inputLuasBangunan by remember { mutableStateOf("") }
    var inputLuasTanah by remember { mutableStateOf("") }
    var inputKamarMandi by remember { mutableStateOf("") }
    var inputGarasi by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.house_price_estimation)) },
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
                    text = stringResource(R.string.current_savings),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                )
                OutlinedTextField(
                    value = inputTabungan,
                    onValueChange = { newInput ->
                        inputTabungan = newInput
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
                            imageVector = Icons.Default.Edit,
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

                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.house_type),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .align(Alignment.BottomCenter)
                            .background(Color.Black)
                    )
                }
                Text(
                    text = stringResource(R.string.building_area),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                )
                OutlinedTextField(
                    value = inputLuasBangunan,
                    onValueChange = { newInput ->
                        inputLuasBangunan = newInput
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
                    text = stringResource(R.string.land_area),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                )
                OutlinedTextField(
                    value = inputLuasTanah,
                    onValueChange = { newInput ->
                        inputLuasTanah = newInput
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
                    text = stringResource(R.string.bedroom_amount),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                )
                OutlinedTextField(
                    value = inputKamarTidur,
                    onValueChange = { newInput ->
                        inputKamarTidur = newInput
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
                    text = stringResource(R.string.bathroom_amount),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                )
                OutlinedTextField(
                    value = inputKamarMandi,
                    onValueChange = { newInput ->
                        inputKamarMandi = newInput
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
                    text = stringResource(R.string.garage),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                )
                Row() {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)
                            .weight(0.5f),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        RadioButton(
                            selected = inputGarasi,
                            onClick = { inputGarasi = true }
                        )
                        Text(
                            text = stringResource(R.string.trueWord),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)
                            .weight(0.5f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = !inputGarasi,
                            onClick = { inputGarasi = false }
                        )
                        Text(
                            text = stringResource(R.string.falseWord),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
                val isButtonEnabled = inputTabungan.isNotEmpty() &&
                        inputLuasBangunan.isNotEmpty() &&
                        inputLuasTanah.isNotEmpty() &&
                        inputKamarTidur.isNotEmpty() &&
                        inputKamarMandi.isNotEmpty()
                val isInputValid = inputTabungan.toDoubleOrNull()?.let { it >= 0 } == true &&
                        inputLuasBangunan.toDoubleOrNull()?.let { it >= 0 } == true &&
                        inputLuasTanah.toDoubleOrNull()?.let { it >= 0 } == true &&
                        inputKamarTidur.toDoubleOrNull()?.let { it >= 0 } == true &&
                        inputKamarMandi.toDoubleOrNull()?.let { it >= 0 } == true

                Row(Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)) {
                    Button(
                        onClick = {
                            val user = FirebaseAuth.getInstance().currentUser
                            viewModel.predict(
                                inputLuasBangunan = inputLuasBangunan,
                                inputLuasTanah = inputLuasTanah,
                                inputKamarTidur = inputKamarTidur,
                                inputKamarMandi = inputKamarMandi,
                                inputGarasi = inputGarasi,
                                selectedYear = selectedYear,
                                user = user
                            )
                        },
                        enabled = isButtonEnabled && isInputValid && (inputLuasBangunan.toDouble() <= inputLuasTanah.toDouble()),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = if (!isButtonEnabled) stringResource(R.string.complete_form)
                            else if (inputLuasBangunan.toDouble() > inputLuasTanah.toDouble()) stringResource(
                                R.string.building_area_can_t_be_higher_than_land_area
                            )
                            else if (!isInputValid) stringResource(R.string.cant_negative)
                            else if (isButtonEnabled && isInputValid) stringResource(R.string.predict)
                            else stringResource(R.string.complete_form),
                            fontSize = 16.sp
                        )
                    }
                    LaunchedEffect(predictionResponse) {
                        val pricePrediction = predictionResponse?.pricePrediction
                        val priceNow = predictionResponse?.priceNow
                        if (pricePrediction != null && priceNow != null) {
                            println("Price Prediction: $pricePrediction")
                            println("Price Now: $priceNow")

                            navController.navigate("PerkiraanDetail/${inputTabungan}/${inputLuasBangunan}/${inputLuasTanah}/${inputKamarTidur}/${inputKamarMandi}/${if (inputGarasi) "1" else "0"}/${selectedYear}/$pricePrediction/$priceNow") {
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