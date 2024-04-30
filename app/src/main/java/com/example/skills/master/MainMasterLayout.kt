package com.example.skills.master

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.skills.master.navBar.MasterBottomNavigation
import com.example.skills.master.navigation.account.ScreenMaster
import com.example.skills.master.navigation.account.SetupMasterNavGraph
import com.example.skills.role.ScreenRole

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainMasterLayout() {
    val navController = rememberNavController()
    val hideList = setOf(
        ScreenRole.Master.GoogleCalendar.route,
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