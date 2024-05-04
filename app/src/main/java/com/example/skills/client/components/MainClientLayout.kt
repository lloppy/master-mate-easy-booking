package com.example.skills.client.components

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.skills.client.navigation.account.SetupClientNavGraph
import com.example.skills.client.navigation.navBar.ClientBottomNavigation
import com.example.skills.role.ScreenRole

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