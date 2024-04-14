package com.example.skills.navigation.client.account

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.client.account.CalendarScreen
import com.example.skills.client.account.ChatsScreen
import com.example.skills.client.account.MainLayout
import com.example.skills.client.account.MainUserScreen
import com.example.skills.client.registration.LogInScreen
import com.example.skills.client.registration.RegistrationScreen

@Composable
fun SetupNavGraph(
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = Screen.UserHomeScreen.route) {
        composable(route = Screen.UserHomeScreen.route,){
            MainUserScreen()
        }
        composable(route = Screen.CalendarScreen.route,){
            CalendarScreen()
        }
        composable(route = Screen.ChatsScreen.route,){
            ChatsScreen()
        }
    }
}