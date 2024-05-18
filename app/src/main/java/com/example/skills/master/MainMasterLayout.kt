package com.example.skills.master

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.skills.general.navigation.master_nav_bar.MasterBottomNavigation
import com.example.skills.general.navigation.master_nav_bar.SetupMasterNavGraph
import com.example.skills.general.ScreenRole

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainMasterLayout() {
    val navController = rememberNavController()
    val hideList = setOf(
        ScreenRole.Master.EditProfile.route,
        ScreenRole.Master.ShareProfile.route,
        ScreenRole.Master.Notifications.route,
        ScreenRole.Master.PasswordSettings.route
    )

    val screen = navController.currentBackStackEntryAsState().value
    val showBottomBar = screen?.destination?.route !in hideList

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                MasterBottomNavigation(navController = navController)
            }
        }) {
        SetupMasterNavGraph(navHostController = navController)
    }
}