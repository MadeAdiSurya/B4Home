package com.okta.capstonetestapp.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.okta.capstonetestapp.R
import com.okta.capstonetestapp.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ProfileScreen(navController: NavHostController = rememberNavController()) {
    val viewModel: ProfileViewModel = viewModel()
    val email by viewModel.email.collectAsState()
    val openDialog = remember { mutableStateOf(false) }
    val navigateToWelcome = remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()

    DisposableEffect(Unit) {
        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null && navigateToWelcome.value) {
                navController.navigate(Screen.Welcome.route) {
                    popUpTo(Screen.Profile.route) { saveState = true }
                    restoreState = false
                    launchSingleTop = true
                }

            }
        }

        auth.addAuthStateListener(authStateListener)

        onDispose {
            auth.removeAuthStateListener(authStateListener)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile") },
                navigationIcon = {},
                actions = {},
                colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary)
            )
        }
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 64.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(160.dp)
                        .aspectRatio(1f)
                        .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                ) {
                    Image(
                        painter = painterResource(R.drawable.house),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(150.dp)
                            .aspectRatio(1f)
                            .clip(CircleShape)
                            .align(Alignment.Center)
                    )
                }
                Text(
                    text = email,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Button(
                    onClick = {
                        openDialog.value = true

                    },
                    modifier = Modifier
                        .padding(top = 48.dp)
                        .height(48.dp)
                ) {
                    Text(text = "Logout")
                }
            }

            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "Success!")
                    },
                    text = {
                        Text("Logout")
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                Firebase.auth.signOut()
                                openDialog.value = false
                                navigateToWelcome.value = true
                            }
                        ) {
                            Text("Lanjut")
                        }
                    }
                )
            }
        }
    }
}