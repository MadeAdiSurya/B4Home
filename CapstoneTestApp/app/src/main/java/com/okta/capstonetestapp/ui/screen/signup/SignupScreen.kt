package com.okta.capstonetestapp.ui.screen.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.okta.capstonetestapp.R
import com.okta.capstonetestapp.navigation.Screen
import com.okta.capstonetestapp.ui.components.EmailText
import com.okta.capstonetestapp.ui.components.PasswordText


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SignupScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    var isLoading by remember { mutableStateOf(false) }

    var inputEmail by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }
    var inputPassword by remember { mutableStateOf("") }
    var isPasswordTextTapped by remember { mutableStateOf(false) }
    val isPasswordValid = inputPassword.length >= 8
    val openDialog = remember { mutableStateOf(false) }
    val signupResponse = remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()


    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.housepricing),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight(0.4f)
            )
            Text(
                text = stringResource(R.string.signup_tagline1),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp, top = 8.dp)
            )
            Text(
                text = stringResource(R.string.signup_tagline2),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 8.dp)
            )
            Text(
                text = stringResource(R.string.email),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp)
            )
            EmailText(
                email = inputEmail,
                onEmailChange = { newEmail -> inputEmail = newEmail },
                isEmailValid = isEmailValid,
                onEmailValidChange = { isValid -> isEmailValid = isValid }
            )
            Text(
                text = stringResource(R.string.password),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 32.dp)
            )
            PasswordText(
                input = inputPassword,
                onValueChange = {
                    inputPassword = it
                    isPasswordTextTapped = true
                },
                isError = !isPasswordValid,
                isTapped = isPasswordTextTapped
            )
            Button(
                onClick = {
                    isLoading = true
                    auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                        .addOnCompleteListener { task ->
                            isLoading = false
                            if (task.isSuccessful) {
                                // User is successfully registered and logged in
                                openDialog.value = true
                                signupResponse.value = true
                            } else {
                                // Registration failed
                                openDialog.value = true
                                signupResponse.value = false
                            }
                        }
                },
                enabled = isEmailValid && inputEmail.isNotEmpty(),
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp, top = 16.dp)
                    .fillMaxWidth()
                    .height(64.dp)
            ) {
                Text(
                    text = stringResource(R.string.sign_up),
                    fontSize = 16.sp,
                )
            }
            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        if (signupResponse.value) {
                            Text(text = stringResource(R.string.yeah))
                        } else {
                            Text(text = stringResource(R.string.error))
                        }

                    },
                    text = {
                        if (signupResponse.value) {
                            Text(stringResource(R.string.successfully_register))
                        } else {
                            Text(stringResource(R.string.invalid))
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                if (signupResponse.value) {
                                    openDialog.value = false
                                    navController.navigate(Screen.Home.route) {
                                        popUpTo(Screen.Welcome.route) { saveState = false }
                                        restoreState = true
                                        launchSingleTop = true
                                    }
                                } else {
                                    openDialog.value = false
                                }
                            }
                        ) {
                            Text(stringResource(R.string.continueWord))
                        }
                    }
                )
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