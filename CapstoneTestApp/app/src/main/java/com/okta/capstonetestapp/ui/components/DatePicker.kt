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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.okta.capstonetestapp.R

@Composable
fun MonthYearPicker(
    selectedYear: Int,
    onYearSelected: (Int) -> Unit
) {
    Column {
        Column(
            modifier = Modifier
                .border(border = BorderStroke(width = 1.dp, Color.LightGray), shape = RoundedCornerShape(8.dp))
        ) {
            Text(
                text = stringResource(R.string.year),
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
                range = 2024..2099,
                onValueChange = onYearSelected
            )
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



