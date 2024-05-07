package com.example.skills.client

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.skills.general.navigation.client_nav_bar.SetupClientNavGraph
import com.example.skills.general.navigation.client_nav_bar.ClientBottomNavigation
import com.example.skills.general.ScreenRole

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainClientLayout() {
    val navController = rememberNavController()
    val hideList = setOf(
        ScreenRole.Client.GoogleCalendar.route,
        ScreenRole.Client.EditProfile.route,
        ScreenRole.Client.Notifications.route,
        ScreenRole.Client.PasswordSettings.route
    )

    val screen = navController.currentBackStackEntryAsState().value
    val showBottomBar = screen?.destination?.route !in hideList

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                ClientBottomNavigation(navController = navController)
            }
        }) {
        SetupClientNavGraph(navHostController = navController)
    }
}