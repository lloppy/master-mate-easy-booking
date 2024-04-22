package com.example.skills.client.navigation.account

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.client.account.CalendarScreen
import com.example.skills.client.account.ChatsScreen
import com.example.skills.client.account.MainClientScreen

@Composable
fun SetupClientNavGraph(
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = ScreenClient.ClientHomeScreen.route) {
        composable(route = ScreenClient.ClientHomeScreen.route,){
            MainClientScreen()
        }
        composable(route = ScreenClient.ClientCalendarScreen.route,){
            CalendarScreen()
        }
        composable(route = ScreenClient.ClientChatsScreen.route,){
            ChatsScreen()
        }
    }
}