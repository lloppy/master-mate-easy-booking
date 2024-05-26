package com.example.skills.ui.master

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.navigation.nav_bar.master.MasterBottomNavigation
import com.example.skills.navigation.nav_bar.master.SetupMasterNavGraph
import com.example.skills.navigation.ScreenRole

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainMasterLayout(navController: NavHostController, mainViewmodel: MainViewModel) {
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
        SetupMasterNavGraph(navHostController = navController, mainViewModel = mainViewmodel)
    }
}