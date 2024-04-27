package com.example.skills.master.navigation.account

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.client.account.CalendarScreen
import com.example.skills.master.MainMasterScreen
import com.example.skills.master.components.MasterClientServicesScreen
import com.example.skills.master.components.MasterMyServicesScreen
import com.example.skills.master.components.MasterSettingsScreen
import com.example.skills.master.creatingService.MainCreationLayout

@Composable
fun SetupMasterNavGraph(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenMater.MasterHomeScreen.route
    ) {
        // person
        composable(route = ScreenMater.MasterHomeScreen.route) {
            MainMasterScreen()
        }
        //calendar
        composable(route = ScreenMater.MasterCalendarScreen.route) {
            CalendarScreen()
        }
        // done checkbox
        composable(route = ScreenMater.MasterCreateServiceScreen.route) {
            MasterClientServicesScreen()
        }
        // server
        composable(route = ScreenMater.MasterServerScreen.route) {
            MasterMyServicesScreen()
        }
        // settings
        composable(route = ScreenMater.MasterSettingsScreen.route) {
            MasterSettingsScreen()
        }

        // all layout, setup navigation
        composable(route = ScreenMater.MainCreationLayout.route) {
            MainCreationLayout()
        }
    }
}