package com.example.skills.navigation.client.registration

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.master.MainMasterLayout
import com.example.skills.master.registration.LogInMasterScreen
import com.example.skills.master.registration.RegistrationMasterScreen
import com.example.skills.navigation.master.registration.MasterScreen

@Composable
fun SetupMasterNavGraph(
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = MasterScreen.MasterLogIn.route) {
        composable(route = MasterScreen.MasterLogIn.route) {
            LogInMasterScreen(navController = navHostController)
        }
        composable(route = MasterScreen.MasterRegistration.route) {
            RegistrationMasterScreen(navController = navHostController)
        }
        composable(route = MasterScreen.MainMasterLayout.route) {
            MainMasterLayout()
        }
        composable(route = MasterScreen.MainMasterLayout.route) {
            MainMasterLayout()
        }
    }
}