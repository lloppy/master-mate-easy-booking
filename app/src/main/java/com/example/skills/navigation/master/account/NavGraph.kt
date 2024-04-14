package com.example.skills.navigation.master.account

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.master.MainMasterScreen
import com.example.skills.master.MasterCreateServiceScreen

@Composable
fun SetupMasterNavGraph(
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = ScreenMater.MasterHomeScreen.route) {
        composable(route = ScreenMater.MasterHomeScreen.route){
            MainMasterScreen()
        }
        composable(route = ScreenMater.MasterCreateServiceScreen.route){
            MasterCreateServiceScreen()
        }

        composable(route = ScreenMater.MasterCreateServiceScreen.route){
            MasterCreateServiceScreen()
        }

        composable(route = ScreenMater.MasterCalendarScreen.route){
//            CalendarScreen()
        }
        composable(route = ScreenMater.MasterChatsScreen.route){
  //          ChatsScreen()
        }
    }
}