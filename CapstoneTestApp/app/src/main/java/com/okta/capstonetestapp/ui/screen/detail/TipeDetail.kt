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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.okta.capstonetestapp.R
import com.okta.capstonetestapp.utils.currencyFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipeDetail (
    navController: NavHostController = rememberNavController(),
    inputHarga: String?,
    selectedYear: String?,
    lbPrediction: String?,
    ltPrediction: String?
){
    val formattedPrice: Any = currencyFormat(inputHarga) ?: "Not Found"
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text =stringResource(R.string.find_house_preference)) },
                navigationIcon = {},
                actions = {},
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
                painter = when {
                    lbPrediction?.toInt() ?: 0 <= 200 -> painterResource(
                        R.drawable.rumah_lvl1
                    )

                    lbPrediction?.toInt() ?: 0 <= 300 -> painterResource(
                        R.drawable.rumah_lvl2
                    )

                    lbPrediction?.toInt() ?: 0 <= 400 -> painterResource(
                        R.drawable.rumah_lvl3
                    )

                    lbPrediction?.toInt() ?: 0 <= 500 -> painterResource(
                        R.drawable.rumah_lvl4
                    )

                    else -> painterResource(R.drawable.rumah_lvl5)
                },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            )
            Text(
                text = stringResource(R.string.app_name),
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
                text = stringResource(R.string.house_in_south_jakarta),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(R.string.house_prediction_for, selectedYear!!),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Text(
                text = stringResource(R.string.prediction_result),
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
                    text = stringResource(R.string.house_price),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                )
                Text(
                    text = formattedPrice.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                )
            }
            Row (Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)){
                Text(
                    text = stringResource(R.string.land_area),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                )
                Text(
                    text = stringResource(R.string.area_m2, ltPrediction!!),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                )
            }
            Row (Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)){
                Text(
                    text = stringResource(R.string.building_area),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                )
                Text(
                    text = stringResource(R.string.area_m2, lbPrediction!!),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                )
            }
            Row (Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)){
                Text(
                    text = stringResource(R.string.region),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                )
                Text(
                    text = stringResource(R.string.south_jakarta),
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
        }
    }
}
