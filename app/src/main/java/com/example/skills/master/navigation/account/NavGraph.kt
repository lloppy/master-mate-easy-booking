package com.example.skills.master.navigation.account

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.client.account.CalendarScreen
import com.example.skills.master.MainMasterScreen
import com.example.skills.master.components.ChangeCategoryScreen
import com.example.skills.master.components.CreateServiceCardScreen
import com.example.skills.master.components.CreateServiceScreen
import com.example.skills.master.components.EditPasswordScreen
import com.example.skills.master.components.EditProfileScreen
import com.example.skills.master.components.EditServiceCardScreen
import com.example.skills.master.components.GoogleCalendarScreen
import com.example.skills.master.components.MasterClientServicesScreen
import com.example.skills.master.components.MasterMyServicesScreen
import com.example.skills.master.components.MasterSettingsScreen
import com.example.skills.master.components.NotificationSettingsScreen
import com.example.skills.master.creatingService.MainCreationLayout
import com.example.skills.role.ScreenRole

@Composable
fun SetupMasterNavGraph(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenMaster.MasterHomeScreen.route
    ) {
        // person
        composable(route = ScreenMaster.MasterHomeScreen.route) {
            MainMasterScreen()
        }
        //calendar
        composable(route = ScreenMaster.MasterCalendarScreen.route) {
            CalendarScreen()
        }
        // done checkbox
        composable(route = ScreenMaster.MasterCreateServiceScreen.route) {
            MasterClientServicesScreen()
        }
        // server
        composable(route = ScreenMaster.MasterServerScreen.route) {
            MasterMyServicesScreen(
                navigateToCreateCategory = {
                    navHostController.navigate(ScreenRole.Master.CreateService.route)
                },
                navigateToChangeCategory = {
                    navHostController.navigate(ScreenRole.Master.ChangeCategory.route)
                },
                navController = navHostController
            )
        }

        // all layout, setup navigation
        composable(route = ScreenMaster.MainCreationLayout.route) {
            MainCreationLayout()
        }

        // settings
        composable(route = ScreenMaster.MasterSettingsScreen.route) {
            MasterSettingsScreen(
                navigateToEditAccount = {
                    navHostController.navigate(ScreenRole.Master.EditProfile.route)
                },
                navigateToEditPassword = {
                    navHostController.navigate(ScreenRole.Master.PasswordSettings.route)
                },
                navigateToCalendar = {
                    navHostController.navigate(ScreenRole.Master.GoogleCalendar.route)
                },
                navigateToNotifications = {
                    navHostController.navigate(ScreenRole.Master.Notifications.route)
                },
                exit = {
                    // крашится, потому что другой роут, нужно потом полностью закрывать приложение
                    navHostController.navigate(ScreenRole.Master.LogIn.route)
                }
            )
        }

        composable(ScreenRole.Master.GoogleCalendar.route) {
            GoogleCalendarScreen(
                navController = navHostController,
                navigateToMain = {
                    navHostController.navigate(ScreenMaster.MasterHomeScreen.route)
                }
            )
        }

        composable(ScreenRole.Master.PasswordSettings.route) {
            EditPasswordScreen(
                navController = navHostController,
                navigateToMain = {
                    navHostController.navigate(ScreenMaster.MasterHomeScreen.route)
                }
            )
        }

        composable(ScreenRole.Master.EditProfile.route) {
            EditProfileScreen(
                navController = navHostController,
                navigateToMain = {
                    navHostController.navigate(ScreenMaster.MasterHomeScreen.route)
                }
            )
        }

        composable(ScreenRole.Master.Notifications.route) {
            NotificationSettingsScreen(
                navController = navHostController,
                navigateToMain = {
                    navHostController.navigate(ScreenMaster.MasterHomeScreen.route)
                }
            )
        }

        composable(ScreenRole.Master.CreateService.route) {
            CreateServiceScreen(
                navController = navHostController
            )
        }

        composable(ScreenRole.Master.ChangeCategory.route) {
            ChangeCategoryScreen(
                navController = navHostController
            )
        }

        composable(ScreenRole.Master.CreateServiceCard.route) { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId")
            CreateServiceCardScreen(
                serviceId,
                navController = navHostController
            )
        }

        composable(ScreenRole.Master.EditServiceCard.route) { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId")
            EditServiceCardScreen(
                serviceId,
                navController = navHostController
            )
        }

//        composable(ScreenRole.Master.EditServiceCard.route) {
//            EditServiceCardScreen(
//                navController = navHostController
//            )
//        }


    }
}