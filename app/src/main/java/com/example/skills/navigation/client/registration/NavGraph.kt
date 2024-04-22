package com.example.skills.navigation.client.registration

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.client.account.MainClientLayout
import com.example.skills.client.registration.ChooseRoleScreen
import com.example.skills.client.registration.LogInScreen
import com.example.skills.client.registration.RegistrationScreen
import com.example.skills.master.MainMasterLayout

@Composable
fun SetupNavGraph(
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = Screen.ChooseRole.route) {
        composable(route = Screen.ChooseRole.route,){
            ChooseRoleScreen(navController = navHostController)
        }
        composable(route = Screen.ClientLogIn.route,){
            LogInScreen(navController = navHostController)
        }
        composable(route = Screen.ClientRegistration.route,){
            RegistrationScreen(navController = navHostController)
        }
        composable(route = Screen.MainClientLayout.route,){
            MainClientLayout()
        }
        composable(route = Screen.MainMasterLayout.route,){
            MainMasterLayout()
        }
    }
}