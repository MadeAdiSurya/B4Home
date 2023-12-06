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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.okta.capstonetestapp.navigation.Screen
import com.okta.capstonetestapp.ui.components.MonthYearPicker
import com.okta.capstonetestapp.ui.components.monthList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstimasiForm(
    navController: NavHostController = rememberNavController(),
) {
    var selectedMonth by remember { mutableStateOf(monthList[0]) }
    var selectedYear by remember { mutableStateOf(2023) }
    var selectedDate by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    var inputKamarTidur by remember { mutableStateOf("") }
    var inputKamarMandi by remember { mutableStateOf("") }
    var inputGarasi by remember { mutableStateOf(false) }
    var inputHarga by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Estimasi Menabung") },
                navigationIcon = {},
                actions = {
                    // Add action icons here
                    IconButton(onClick = {
                        navController.navigate(Screen.About.route) {
                            popUpTo(Screen.EstimasiForm.route) { saveState = true }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }) {
                        Icon(Icons.Filled.Info, contentDescription = "About")
                    }
                    IconButton(onClick = {
                        navController.navigate(Screen.Profile.route) {
                            popUpTo(Screen.EstimasiForm.route) { saveState = true }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }) {
                        Icon(Icons.Filled.AccountCircle, contentDescription = "Profile")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Target Bulan dan Tahun Beli Rumah",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
            )
            LaunchedEffect(selectedMonth, selectedYear) {
                selectedDate = "$selectedMonth/$selectedYear"
            }
            Row {
                OutlinedTextField(
                    value = selectedDate,
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
                                selectedMonth = selectedMonth,
                                selectedYear = selectedYear,
                                onMonthSelected = { month ->
                                    selectedMonth = month
                                    // Do something with the selected month
                                },
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
                    text = "Tipe Rumah",
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
                text = "Jumlah Kamar Tidur",
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
                text = "Jumlah Kamar Mandi",
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
                text = "Garasi",
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
                        text = "True",
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
                        text = "False",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            Text(
                text = "Harga Rumah Yang Dicari",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
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
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            )
            Row(Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f)
                        .height(64.dp)
                        .padding(end = 8.dp)
                ) {
                    Text(text = "Search House", fontSize = 16.sp)
                }
                Button(
                    onClick = {
                                navController.navigate(Screen.EstimasiDetail.route) {
                                    popUpTo(Screen.Home.route) { saveState = true }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                              },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f)
                        .height(64.dp)
                        .padding(start = 8.dp)
                ) {
                    Text(text = "Predict", fontSize = 16.sp)
                }
            }

        }
    }
}