package com.okta.capstonetestapp.ui.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.okta.capstonetestapp.R
import com.okta.capstonetestapp.navigation.Screen

@Preview
@Composable
fun WelcomeScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){

    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(R.drawable.housepricing),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight(0.5f)
        )
        Text(
            text = stringResource(R.string.welcome_to_b4home),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp, top = 8.dp)
        )
        Text(
            text = stringResource(R.string.welcoming_sentence),
            fontSize = 14.sp,
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 8.dp)
        )
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ){
            Button(
                onClick = {
                    navController.navigate(Screen.Login.route){
                        popUpTo(Screen.Welcome.route) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    } },
                Modifier
                    .padding(start = 32.dp, end = 16.dp)
                    .weight(1f),
            ) {
                Text(text = stringResource(R.string.log_in))
            }
            Button(
                onClick = {
                    navController.navigate(Screen.Signup.route) {
                        popUpTo(Screen.Welcome.route) { saveState = true }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                Modifier
                    .padding(start = 16.dp, end = 32.dp)
                    .weight(1f),
            ) {
                Text(text = stringResource(R.string.sign_up))
            }
        }
    }
}