package com.example.skills.client.navigation.account

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.client.components.a.ClientMastersScreen
import com.example.skills.client.components.b.ClientBookingsScreen
import com.example.skills.client.components.c.EditClientProfileScreen
import com.example.skills.master.components.e.EditPasswordScreen
import com.example.skills.master.components.e.GoogleCalendarScreen
import com.example.skills.master.components.e.MasterSettingsScreen
import com.example.skills.master.components.e.NotificationSettingsScreen
import com.example.skills.role.ScreenRole

@Composable
fun SetupClientNavGraph(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenClient.ClientBookingsScreen.route
    ) {
        // server
        composable(route = ScreenClient.ClientMastersScreen.route) {
            ClientMastersScreen(
            )
        }

        // done checkbox
        composable(route = ScreenClient.ClientBookingsScreen.route) {
            ClientBookingsScreen()
        }

        // settings
        composable(route = ScreenClient.ClientSettingsScreen.route) {
            // reused component
            MasterSettingsScreen(
                navigateToEditAccount = {
                    // тут потом быть осторожнее, перепроверить что все норм
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
                // no navigateToMain
                navigateToMain = {
                    navHostController.navigate(ScreenClient.ClientBookingsScreen.route)
                }
            )
        }

        composable(ScreenRole.Client.PasswordSettings.route) {
            EditPasswordScreen(
                navController = navHostController,
                navigateToMain = {
                    navHostController.navigate(ScreenClient.ClientBookingsScreen.route)
                }
            )
        }

        composable(ScreenRole.Client.EditProfile.route) {
            EditClientProfileScreen(
                navController = navHostController,
                navigateToMain = {
                    navHostController.navigate(ScreenClient.ClientBookingsScreen.route)
                }
            )
        }

        composable(ScreenRole.Client.Notifications.route) {
            NotificationSettingsScreen(
                navController = navHostController
            )
        }
    }

}