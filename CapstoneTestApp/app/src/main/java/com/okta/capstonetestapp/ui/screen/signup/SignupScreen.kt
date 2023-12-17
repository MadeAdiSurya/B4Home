package com.okta.capstonetestapp.ui.screen.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.okta.capstonetestapp.R
import com.okta.capstonetestapp.navigation.Screen
import com.okta.capstonetestapp.ui.components.EmailText
import com.okta.capstonetestapp.ui.components.PasswordText

private lateinit var database: DatabaseReference

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SignupScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    var inputNama by remember { mutableStateOf("") }
    var inputEmail by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }
    var inputPassword by remember { mutableStateOf("") }
    var isPasswordTextTapped by remember { mutableStateOf(false) }
    val isPasswordValid = inputPassword.length >= 8
    var output by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()

    database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://b4home-login-register-default-rtdb.firebaseio.com/")


    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(R.drawable.housepricing),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight(0.4f)
        )
        Text(
            text = "No Account? Don’t Worry",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp, top = 8.dp)
        )
        Text(
            text = "Let’s create one",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 8.dp)
        )
        Text(
            text = "Nama",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp, top = 16.dp)
        )
        OutlinedTextField(
            value = inputNama,
            onValueChange = { newInput1 ->
                inputNama = newInput1
                output = newInput1
            },
            leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = null) },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, bottom = 8.dp)
        )
        Text(
            text = "Email",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp)
        )
//        OutlinedTextField(
//            value = inputEmail,
//            label = { Text(stringResource(R.string.enter_email)) },
//            onValueChange = { newInput2 ->
//                inputEmail = newInput2
//                output = newInput2
//            },
//            leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 32.dp, end = 32.dp, bottom = 8.dp)
//        )
        EmailText(
            email = inputEmail,
            onEmailChange = { newEmail -> inputEmail = newEmail },
            isEmailValid = isEmailValid,
            onEmailValidChange = { isValid -> isEmailValid = isValid }
        )
        Text(
            text = "Password",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(horizontal = 32.dp)
        )
        PasswordText(
            input = inputPassword,
            onValueChange = {
                inputPassword = it
                isPasswordTextTapped = true},
            isError = !isPasswordValid,
            isTapped = isPasswordTextTapped
        )
        Button(
            onClick = {
                auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // User is successfully registered and logged in

                        } else {
                            // Registration failed
                            // ...
                        }
                    }
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Welcome.route) { saveState = true }
                    restoreState = true
                    launchSingleTop = true
                }
            },
            enabled = isEmailValid && inputEmail.isNotEmpty(),
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp, top = 16.dp)
                .fillMaxWidth()
                .height(64.dp)
        ) {
            Text(
                text = "Sign Up",
                fontSize = 16.sp,
            )
        }
    }
}