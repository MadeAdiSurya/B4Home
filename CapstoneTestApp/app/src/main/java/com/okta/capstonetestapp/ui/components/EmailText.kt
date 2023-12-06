package com.okta.capstonetestapp.ui.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.okta.capstonetestapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailText(
    email: String,
    onEmailChange: (String) -> Unit,
    isEmailValid: Boolean,
    onEmailValidChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        OutlinedTextField(
            value = email,
            onValueChange = { newEmail ->
                onEmailChange(newEmail)
                onEmailValidChange(android.util.Patterns.EMAIL_ADDRESS.matcher(newEmail).matches())
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
            leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) },
            isError = !isEmailValid,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, bottom = 8.dp)
        )

        if (!isEmailValid && email.isNotEmpty()) {
            Text(
                text = "Please enter a valid email",
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp, bottom = 8.dp)
            )
        }
    }
}