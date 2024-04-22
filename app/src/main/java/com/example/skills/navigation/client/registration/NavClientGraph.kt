package com.example.skills.navigation.client.registration

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.client.account.MainClientLayout
import com.example.skills.navigation.role.ChooseRoleScreen
import com.example.skills.client.registration.LogInClientScreen
import com.example.skills.client.registration.RegistrationClientScreen

@Composable
fun SetupClientNavGraph(
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = ClientScreen.ClientLogIn.route) {
        composable(route = ClientScreen.ClientLogIn.route,){
            LogInClientScreen(navController = navHostController)
        }
        composable(route = ClientScreen.ClientRegistration.route,){
            RegistrationClientScreen(navController = navHostController)
        }
        composable(route = ClientScreen.MainClientLayout.route,){
            MainClientLayout()
        }
    }
}