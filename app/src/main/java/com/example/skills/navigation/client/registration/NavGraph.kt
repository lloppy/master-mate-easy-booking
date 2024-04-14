package com.example.skills.navigation.client.registration

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.client.account.MainLayout
import com.example.skills.client.registration.LogInScreen
import com.example.skills.client.registration.RegistrationScreen

@Composable
fun SetupNavGraph(
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = Screen.LogIn.route) {
        composable(route = Screen.LogIn.route,){
            LogInScreen(navController = navHostController)
        }
        composable(route = Screen.Registration.route,){
            RegistrationScreen(navController = navHostController)
        }
        composable(route = Screen.MainUserLayout.route,){
            MainLayout()
        }
    }
}