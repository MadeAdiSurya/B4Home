package com.okta.capstonetestapp.ui.screen.home

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.okta.capstonetestapp.R
import com.okta.capstonetestapp.model.HomeList
import com.okta.capstonetestapp.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
){
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.home)) },
                navigationIcon = {},
                actions = {
                    // Add action icons here
                    IconButton(onClick = {
                        navController.navigate(Screen.About.route) {
                            popUpTo(Screen.Home.route) { saveState = true }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }) {
                        Icon(Icons.Filled.Info, contentDescription = stringResource(R.string.about))
                    }
                    IconButton(onClick = {
                        navController.navigate(Screen.Profile.route) {
                            popUpTo(Screen.Home.route) { saveState = true }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }) {
                        Icon(Icons.Filled.AccountCircle, contentDescription = stringResource(R.string.profile))
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary, titleContentColor =  MaterialTheme.colorScheme.onPrimary)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            HomeList.forEach { homeListData ->
                Spacer(modifier = modifier.padding(bottom = 8.dp))
                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                        .clickable {
                            when (homeListData.pageName) {
                                "PerkiraanForm" -> navController.navigate(Screen.PerkiraanForm.route) {
                                    popUpTo(Screen.Home.route) { saveState = true }
                                    restoreState = true
                                    launchSingleTop = true
                                }

                                "TipeForm" -> navController.navigate(Screen.TipeForm.route) {
                                    popUpTo(Screen.Home.route) { saveState = true }
                                    restoreState = true
                                    launchSingleTop = true
                                }

                                "KprForm" -> navController.navigate(Screen.KprForm.route) {
                                    popUpTo(Screen.Home.route) { saveState = true }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            }

                        },
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Box {
                        AsyncImage(
                            model = homeListData.imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(225.dp)
                        )
                    }
                    Text(
                        text = homeListData.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 4.dp),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black,
                            shadow = Shadow(
                                color = Color.White,
                                offset = Offset(2f, 2f),
                                blurRadius = 6f
                            )
                        )
                    )
                    Text(
                        text = homeListData.description,
                        fontSize = 12.sp,
                        maxLines = 2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
                        style = TextStyle(lineHeight = 16.sp)
                    )
                }
            }
            Spacer(modifier = Modifier.padding(bottom = 24.dp))
        }
    }
}