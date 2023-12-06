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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.okta.capstonetestapp.R
import com.okta.capstonetestapp.navigation.Screen
import com.okta.capstonetestapp.ui.components.MonthYearPicker
import com.okta.capstonetestapp.ui.components.monthList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipeForm(
    navController: NavHostController = rememberNavController(),
) {
    var inputTabungan by remember { mutableStateOf("") }

    var selectedMonth by remember { mutableStateOf(monthList[0]) }
    var selectedYear by remember { mutableStateOf(2023) }
    var selectedDate by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text ="Cari Tipe Rumah") },
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
                        Icon(Icons.Filled.Info, contentDescription = "About")
                    }
                    IconButton(onClick = {
                        navController.navigate(Screen.Profile.route) {
                            popUpTo(Screen.TipeForm.route) { saveState = true }
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
                text = "Nominal tabungan per bulan",
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
                        painterResource(R.drawable.baseline_attach_money_24),
                        contentDescription = null
                    )
                },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal= 16.dp,)
            )
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
            Row (Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)){
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
                                navController.navigate(Screen.TipeDetail.route) {
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