package com.okta.capstonetestapp.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.okta.capstonetestapp.R
import com.okta.capstonetestapp.utils.currencyFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KprDetail(
    inputHarga: String?,
    inputSukuBunga: String?,
    inputUangMuka: String?,
    inputJangkaWaktu: String?,
    debtsCount: String?,
    monthlyInstallmentCount: String?,
    totalCount: String?,
) {
    val formattedPrice: Any = currencyFormat(inputHarga) ?: "Not Found"
    val formattedDownPayment: Any = currencyFormat(inputUangMuka) ?: "Not Found"
    val formattedDebts: Any = currencyFormat(debtsCount) ?: "Not Found"
    val formattedMonthlyInstallment: Any = currencyFormat(monthlyInstallmentCount) ?: "Not Found"
    val formattedTotalCount: Any = currencyFormat(totalCount) ?: "Not Found"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.house_price_estimation)) },
                navigationIcon = {},
                actions = {},
                colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary)
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(R.string.kpr_title),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black)
                    .padding(top = 4.dp)
            )
            Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
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
            Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    text = "Interest Rate",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                )
                Text(
                    text = "$inputSukuBunga %",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                )
            }
            Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    text = stringResource(R.string.down_payment),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                )
                Text(
                    text = formattedDownPayment.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                )
            }
            Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    text = stringResource(R.string.time_period_year),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                )
                Text(
                    text = inputJangkaWaktu!!,
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
                    .padding(top = 4.dp)
            )
            Text(
                text = stringResource(R.string.result),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black)
                    .padding(top = 4.dp)
            )
            Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    text = stringResource(R.string.debts),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                )
                Text(
                    text = formattedDebts.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                )
            }
            Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    text = stringResource(R.string.monthly_installment),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                )
                Text(
                    text = formattedMonthlyInstallment.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                )
            }
            Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    text = stringResource(R.string.total),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                )
                Text(
                    text = formattedTotalCount.toString(),
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
        }
    }
}