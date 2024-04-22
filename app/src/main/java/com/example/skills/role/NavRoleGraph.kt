package com.example.skills.role

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.client.account.MainClientLayout
import com.example.skills.client.registration.LogInClientScreen
import com.example.skills.client.registration.RegistrationClientScreen
import com.example.skills.master.MainMasterLayout
import com.example.skills.master.components.DoneRegistrationScreen
import com.example.skills.master.registration.LogInMasterScreen
import com.example.skills.master.registration.RegistrationMasterScreen

@Composable
fun SetupRoleNavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = ScreenRole.RoleLayout.route) {
        composable(route = ScreenRole.RoleLayout.route) {
            RolesScreen(navController = navHostController)
        }


        composable(route = ScreenRole.ClientLogIn.route) {
            LogInClientScreen(navController = navHostController)
        }
        composable(route = ScreenRole.ClientRegistration.route) {
            RegistrationClientScreen(navController = navHostController)
        }
        composable(route = ScreenRole.ClientMainLayout.route) {
            MainClientLayout()
        }


        composable(route = ScreenRole.MasterLogIn.route) {
            LogInMasterScreen(navController = navHostController)
        }
        composable(route = ScreenRole.MasterRegistration.route) {
            RegistrationMasterScreen(navController = navHostController)
        }
        composable(route = ScreenRole.MasterMainLayout.route) {
            MainMasterLayout()
        }

        composable(route = ScreenRole.DoneRegistration.route) {
            DoneRegistrationScreen()
        }
    }
}
