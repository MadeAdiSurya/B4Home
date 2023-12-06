package com.okta.capstonetestapp.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Signup : Screen("signup")
    object Home : Screen("home")
    object About : Screen("about")
    object Profile : Screen("profile")
    object PerkiraanForm : Screen("perkiraanForm")
    object TipeForm : Screen("tipeForm")
    object EstimasiForm : Screen("estimasiForm")
    object PerkiraanDetail : Screen("perkiraanDetail")
    object TipeDetail : Screen("tipeDetail")
    object EstimasiDetail : Screen("estimasiDetail")
}