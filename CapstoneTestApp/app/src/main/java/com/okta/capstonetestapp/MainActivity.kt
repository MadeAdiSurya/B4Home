package com.okta.capstonetestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.okta.capstonetestapp.navigation.Screen
import com.okta.capstonetestapp.ui.screen.about.AboutScreen
import com.okta.capstonetestapp.ui.screen.detail.EstimasiDetail
import com.okta.capstonetestapp.ui.screen.detail.PerkiraanDetail
import com.okta.capstonetestapp.ui.screen.detail.TipeDetail
import com.okta.capstonetestapp.ui.screen.form.EstimasiForm
import com.okta.capstonetestapp.ui.screen.form.PerkiraanForm
import com.okta.capstonetestapp.ui.screen.form.TipeForm
import com.okta.capstonetestapp.ui.screen.home.HomeScreen
import com.okta.capstonetestapp.ui.screen.login.LoginScreen
import com.okta.capstonetestapp.ui.screen.profile.ProfileScreen
import com.okta.capstonetestapp.ui.screen.signup.SignupScreen
import com.okta.capstonetestapp.ui.screen.welcome.WelcomeScreen
import com.okta.capstonetestapp.ui.theme.CapstoneTestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapstoneTestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val auth = FirebaseAuth.getInstance()
                    val navController = rememberNavController()
                    val startDestination = if (auth.currentUser != null) Screen.Home.route else Screen.Welcome.route

                    BackHandler {
                        when (navController.currentDestination?.route) {
                            Screen.Home.route -> finish()
                            Screen.Welcome.route -> finish()
                            else -> navController.popBackStack()
                        }
                    }

                    NavHost(
                        navController = navController,
                        startDestination = startDestination,
                    ){
                        composable(Screen.Welcome.route){
                            WelcomeScreen(navController)
                        }
                        composable(Screen.Login.route){
                            LoginScreen(navController)
                        }
                        composable(Screen.Signup.route){
                            SignupScreen(navController)
                        }
                        composable(Screen.Home.route){
                            HomeScreen(navController)
                        }
                        composable(Screen.About.route){
                            AboutScreen()
                        }
                        composable(Screen.Profile.route){
                            ProfileScreen()
                        }
                        composable(Screen.PerkiraanForm.route){
                            PerkiraanForm(navController)
                        }
                        composable(Screen.TipeForm.route){
                            TipeForm(navController)
                        }
                        composable(Screen.EstimasiForm.route){
                            EstimasiForm(navController)
                        }
                        composable(Screen.PerkiraanDetail.route){
                            PerkiraanDetail()
                        }
                        composable(Screen.TipeDetail.route){
                            TipeDetail()
                        }
                        composable(Screen.EstimasiDetail.route){
                            EstimasiDetail()
                        }
                        composable(Screen.Profile.route){
                            ProfileScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
