package com.example.skills.general.navigation.master_nav_bar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.master.a.MainMasterScreen
import com.example.skills.master.b.CalendarScreen
import com.example.skills.master.c.MasterClientServicesScreen
import com.example.skills.master.d.ChangeCategoryScreen
import com.example.skills.master.d.CreateServiceCardScreen
import com.example.skills.master.d.CreateServiceScreen
import com.example.skills.master.d.EditServiceCardScreen
import com.example.skills.master.d.MasterMyServicesScreen
import com.example.skills.master.e.EditPasswordScreen
import com.example.skills.master.e.EditProfileScreen
import com.example.skills.master.e.MasterSettingsScreen
import com.example.skills.master.e.NotificationSettingsScreen
import com.example.skills.general.ScreenRole
import com.example.skills.general.navigation.ScreenMaster
import com.example.skills.master.a.ShareProfileScreen

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
            MainMasterScreen(
                navController = navHostController,
                navigateToShare = {
                    navHostController.navigate(ScreenRole.Master.ShareProfile.route)
                },
            )
        }
        composable(ScreenRole.Master.ShareProfile.route) {
            ShareProfileScreen(
                navController = navHostController
            )
        }

        //calendar
        composable(route = ScreenMaster.MasterCalendarScreen.route) {
            CalendarScreen()
        }
        // done checkbox
        composable(route = ScreenMaster.MasterCreateServiceScreen.route) {
            MasterClientServicesScreen(navHostController)
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


        // settings
        composable(route = ScreenMaster.MasterSettingsScreen.route) {
            MasterSettingsScreen(
                navigateToEditAccount = {
                    navHostController.navigate(ScreenRole.Master.EditProfile.route)
                },
                navigateToEditPassword = {
                    navHostController.navigate(ScreenRole.Master.PasswordSettings.route)
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
                navController = navHostController
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