package com.example.skills.client.navigation.account

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.client.account.CalendarScreen
import com.example.skills.client.account.MainClientScreen
import com.example.skills.client.components.EditClientProfileScreen
import com.example.skills.master.components.EditPasswordScreen
import com.example.skills.master.components.GoogleCalendarScreen
import com.example.skills.master.components.MasterClientServicesScreen
import com.example.skills.master.components.MasterMyServicesScreen
import com.example.skills.master.components.MasterSettingsScreen
import com.example.skills.master.components.NotificationSettingsScreen
import com.example.skills.master.creatingService.MainCreationLayout
import com.example.skills.role.ScreenRole

@Composable
fun SetupClientNavGraph(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenClient.ClientHomeScreen.route
    ) {
        // person
        composable(route = ScreenClient.ClientHomeScreen.route) {
            MainClientScreen()
        }
        //calendar
        composable(route = ScreenClient.ClientCalendarScreen.route) {
            CalendarScreen()
        }
        // done checkbox
        composable(route = ScreenClient.ClientCreateServiceScreen.route) {
            MasterClientServicesScreen()
        }
        // server
        composable(route = ScreenClient.ClientServerScreen.route) {
            MasterMyServicesScreen(
                {}, {}, navHostController
            )
        }

        // all layout, setup navigation
        composable(route = ScreenClient.MainCreationLayout.route) {
            MainCreationLayout()
        }

        // settings
        composable(route = ScreenClient.ClientSettingsScreen.route) {
            MasterSettingsScreen(
                navigateToEditAccount = {
                    navHostController.navigate(ScreenRole.Master.EditProfile.route)
                },
                navigateToEditPassword = {
                    navHostController.navigate(ScreenRole.Client.PasswordSettings.route)
                },
                navigateToCalendar = {
                    navHostController.navigate(ScreenRole.Client.GoogleCalendar.route)
                },
                navigateToNotifications = {
                    navHostController.navigate(ScreenRole.Client.Notifications.route)
                },
                exit = {
                    // крашится, потому что другой роут, нужно потом полностью закрывать приложение
                    navHostController.navigate(ScreenRole.Client.LogIn.route)
                }
            )
        }

        composable(ScreenRole.Client.GoogleCalendar.route) {
            GoogleCalendarScreen(
                navController = navHostController,
                navigateToMain = {
                    navHostController.navigate(ScreenClient.ClientHomeScreen.route)
                }
            )
        }

        composable(ScreenRole.Client.PasswordSettings.route) {
            EditPasswordScreen(
                navController = navHostController,
                navigateToMain = {
                    navHostController.navigate(ScreenClient.ClientHomeScreen.route)
                }
            )
        }

        composable(ScreenRole.Client.EditProfile.route) {
            EditClientProfileScreen(
                navController = navHostController,
                navigateToMain = {
                    navHostController.navigate(ScreenClient.ClientHomeScreen.route)
                }
            )
        }

        composable(ScreenRole.Client.Notifications.route) {
            NotificationSettingsScreen(
                navController = navHostController,
                navigateToMain = {
                    navHostController.navigate(ScreenClient.ClientHomeScreen.route)
                }
            )
        }
    }

}