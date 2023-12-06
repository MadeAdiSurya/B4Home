package com.okta.capstonetestapp.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.okta.capstonetestapp.R
import com.okta.capstonetestapp.ui.components.monthList

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TipeDetail (){
    var inputTabungan by remember { mutableStateOf("") }

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
                title = { Text(text ="Perkiraan Harga Rumah") },
                navigationIcon = {},
                actions = {
                    // Add action icons here
                    IconButton(onClick = { /* Handle "About" icon press */ }) {
                        Icon(Icons.Filled.Info, contentDescription = "About")
                    }
                    IconButton(onClick = { /* Handle "Profile" icon press */ }) {
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
            Image(
                painter = painterResource(R.drawable.house),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            )
            Text(
                text = "B4Home",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    )
            )
            Text(
                text = "Rumah di Jakarta Selatan",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Text(
                text = "Prediksi Rumah Pada Tahun \n 2030",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Text(
                text = "Hasil Prediksi",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 4.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black)
            )
            Row (Modifier.padding(horizontal = 16.dp, vertical = 8.dp)){
                Text(
                    text = "Tabungan per Bulan ",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                )
                Text(
                    text = "Rp7.000.000",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                )
            }
            Row (Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)){
                Text(
                    text = "Luas Tanah",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                )
                Text(
                    text = "700 m2",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                )
            }
            Row (Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)){
                Text(
                    text = "Luas Bangunan",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                )
                Text(
                    text = "400 m2",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                )
            }
            Row (Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)){
                Text(
                    text = "Kamar Tidur",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                )
                Text(
                    text = "3",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                )
            }
            Row (Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)){
                Text(
                    text = "Kamar Mandi",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                )
                Text(
                    text = "3",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                )
            }
            Row (Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)){
                Text(
                    text = "Garasi",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                )
                Text(
                    text = "Ada",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                )
            }
            Row (Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)){
                Text(
                    text = "Daerah",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                )
                Text(
                    text = "Jakarta Selatan",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
            ) {
                Text(text = "Save", fontSize = 16.sp)
            }
        }
    }
}