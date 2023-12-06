package com.okta.capstonetestapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

val monthList = listOf(
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
)


@Composable
fun MonthYearPicker(
    selectedMonth: String,
    selectedYear: Int,
    onMonthSelected: (String) -> Unit,
    onYearSelected: (Int) -> Unit
) {
    Column {
        Column(
            modifier = Modifier
                .border(border = BorderStroke(width = 1.dp, Color.LightGray), shape = RoundedCornerShape(8.dp))
        ){
            Text(
                text = "Month",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    )
                    .padding(vertical = 16.dp)
            )
        Spacer(modifier = Modifier.height(16.dp))
            StringPicker(
                value = selectedMonth,
                options = monthList,
                onValueChange = onMonthSelected
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .border(border = BorderStroke(width = 1.dp, Color.LightGray), shape = RoundedCornerShape(8.dp))
        ) {
            Text(
                text = "Year",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    )
                    .padding(vertical = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            NumberPicker(
                value = selectedYear,
                range = 2023..2099,
                onValueChange = onYearSelected
            )
        }
    }
}

@Composable
fun StringPicker(
    value: String,
    options: List<String>,
    onValueChange: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    Box(
        Modifier
            .height(200.dp)
            .verticalScroll(scrollState)
    ) {
        Column {
            for (option in options) {
                Text(
                    text = option,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onValueChange(option)
                        }
                        .height(40.dp),
                    textAlign = TextAlign.Center,
                    fontSize = if (option == value) 24.sp else 16.sp,
                    fontWeight = if (option == value) FontWeight.Bold else FontWeight.Normal,
                )
            }

        }
    }
}

@Composable
fun NumberPicker(
    value: Int,
    range: IntRange,
    onValueChange: (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    Box(
        Modifier
            .height(200.dp)
            .verticalScroll(scrollState)
    ) {
        Column {
            for (i in range) {
                Text(
                    text = i.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onValueChange(i)
                        }
                        .height(40.dp),
                    textAlign = TextAlign.Center,
                    fontSize = if (i == value) 24.sp else 16.sp,
                    fontWeight = if (i == value) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}



