package com.okta.capstonetestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.okta.capstonetestapp.navigation.Screen
import com.okta.capstonetestapp.ui.screen.about.AboutScreen
import com.okta.capstonetestapp.ui.screen.detail.KprDetail
import com.okta.capstonetestapp.ui.screen.detail.PerkiraanDetail
import com.okta.capstonetestapp.ui.screen.detail.TipeDetail
import com.okta.capstonetestapp.ui.screen.form.KprForm
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
                        composable(Screen.KprForm.route){
                            KprForm(navController)
                        }
                        composable(
                            route = "PerkiraanDetail/{inputTabungan}/{inputLuasBangunan}/{inputLuasTanah}/{inputKamarTidur}/{inputKamarMandi}/{inputGarasi}/{selectedYear}/{pricePrediction}/{priceNow}"
                        ) { backStackEntry ->
                            val arguments = backStackEntry.arguments
                            val inputTabungan = arguments?.getString("inputTabungan")
                            val inputLuasBangunan = arguments?.getString("inputLuasBangunan")
                            val inputLuasTanah = arguments?.getString("inputLuasTanah")
                            val inputKamarTidur = arguments?.getString("inputKamarTidur")
                            val inputKamarMandi = arguments?.getString("inputKamarMandi")
                            val inputGarasi = arguments?.getString("inputGarasi")
                            val selectedYear = arguments?.getString("selectedYear")
                            val pricePrediction = arguments?.getString("pricePrediction")
                            val priceNow = arguments?.getString("priceNow")

                            // Use the arguments in your PerkiraanDetail screen
                            PerkiraanDetail(
                                inputTabungan = inputTabungan,
                                inputLuasBangunan = inputLuasBangunan,
                                inputLuasTanah = inputLuasTanah,
                                inputKamarTidur = inputKamarTidur,
                                inputKamarMandi = inputKamarMandi,
                                inputGarasi = inputGarasi,
                                selectedYear = selectedYear,
                                pricePrediction = pricePrediction,
                                priceNow = priceNow,
                            )
                        }
                        composable(
                            route = "TipeDetail/{inputHarga}/{selectedYear}/{lbPrediction}/{ltPrediction}"
                        ){backStackEntry ->
                            val arguments = backStackEntry.arguments
                            val inputHarga = arguments?.getString("inputHarga")
                            val selectedYear = arguments?.getString("selectedYear")
                            val lbPrediction = arguments?.getString("lbPrediction")
                            val ltPrediction = arguments?.getString("ltPrediction")

                            TipeDetail(
                                inputHarga = inputHarga,
                                selectedYear = selectedYear,
                                lbPrediction = lbPrediction,
                                ltPrediction = ltPrediction
                            )
                        }
                        composable(
                            route = "KprDetail/{inputHarga}/{inputSukuBunga}/{inputUangMuka}/{inputJangkaWaktu}/{debtsCount}/{monthlyInstallmentCount}/{totalCount}"
                        ){backStackEntry ->
                            val arguments = backStackEntry.arguments
                            val inputHarga = arguments?.getString("inputHarga")
                            val inputSukuBunga = arguments?.getString("inputSukuBunga")
                            val inputUangMuka = arguments?.getString("inputUangMuka")
                            val inputJangkaWaktu = arguments?.getString("inputJangkaWaktu")
                            val debtsCount = arguments?.getString("debtsCount")
                            val monthlyInstallmentCount = arguments?.getString("monthlyInstallmentCount")
                            val totalCount = arguments?.getString("totalCount")

                            KprDetail(
                                inputHarga = inputHarga,
                                inputSukuBunga = inputSukuBunga,
                                inputUangMuka = inputUangMuka,
                                inputJangkaWaktu = inputJangkaWaktu,
                                debtsCount = debtsCount,
                                monthlyInstallmentCount = monthlyInstallmentCount,
                                totalCount = totalCount,
                            )
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
