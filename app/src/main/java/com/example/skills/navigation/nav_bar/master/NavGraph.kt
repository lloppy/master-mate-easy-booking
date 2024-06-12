package com.example.skills.navigation.nav_bar.master

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.navigation.ScreenMaster
import com.example.skills.navigation.ScreenRole
import com.example.skills.ui.master.a.MainMasterScreen
import com.example.skills.ui.master.a.ShareProfileScreen
import com.example.skills.ui.master.b.CalendarScreen
import com.example.skills.ui.master.c.MasterClientServicesScreen
import com.example.skills.ui.master.c.ViewScheduleScreen
import com.example.skills.ui.master.d.ChangeCategoryScreen
import com.example.skills.ui.master.d.CreateCategoryScreen
import com.example.skills.ui.master.d.CreateServiceCardScreen
import com.example.skills.ui.master.d.EditServiceCardScreen
import com.example.skills.ui.master.d.MasterMyServicesScreen
import com.example.skills.ui.master.e.EditPasswordScreen
import com.example.skills.ui.master.e.EditProfileScreen
import com.example.skills.ui.master.e.MasterSettingsScreen
import com.example.skills.ui.master.e.NotificationSettingsScreen

@Composable
fun SetupMasterNavGraph(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenMaster.MasterHomeScreen.route
    ) {
        // person
        composable(route = ScreenMaster.MasterHomeScreen.route) {
            MainMasterScreen(
                navigateToShare = {
                    navHostController.navigate(ScreenRole.Master.ShareProfile.route)
                },
                viewModel = mainViewModel
            )
        }
        composable(ScreenRole.Master.ShareProfile.route) {
            ShareProfileScreen(
                navController = navHostController,
                viewModel = mainViewModel
            )
        }

        //calendar
        composable(route = ScreenMaster.MasterCalendarScreen.route) {
            CalendarScreen(
                viewModel = mainViewModel,
                navigateTo = {
                    navHostController.navigate(ScreenRole.Master.ViewSchedule.route)
                }
            )
        }

        // done checkbox
        composable(route = ScreenMaster.MasterCreateServiceScreen.route) {
            MasterClientServicesScreen(mainViewModel)
        }
        // server
        composable(route = ScreenMaster.MasterServerScreen.route) {
            MasterMyServicesScreen(
                navigateToCreateCategory = {
                    navHostController.navigate(ScreenRole.Master.CreateCategory.route)
                },
                navController = navHostController,
                viewModel = mainViewModel
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
//                    navHostController.popBackStack(
//                        route = ScreenMaster.MasterHomeScreen.route,
//                        inclusive = true
//                    )
                    //TODO()
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
                },
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Master.Notifications.route) {
            NotificationSettingsScreen(
                navController = navHostController
            )
        }

        composable(ScreenRole.Master.ViewSchedule.route) {
            ViewScheduleScreen(
                navController = navHostController,
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Master.CreateCategory.route) {
            CreateCategoryScreen(
                navController = navHostController,
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Master.ChangeCategory.route) { backStackEntry ->
            val selectedCategoryName = backStackEntry.arguments?.getString("selectedCategoryName")

            val selectedCategory = selectedCategoryName?.let {
                mainViewModel.getCategoryByName(it)
            }

            if (selectedCategory != null) {
                ChangeCategoryScreen(
                    selectedCategory = selectedCategory,
                    navController = navHostController,
                    viewModel = mainViewModel
                )
            }

        }

        composable(ScreenRole.Master.CreateServiceCard.route) { backStackEntry ->
            val selectedCategoryName = backStackEntry.arguments?.getString("selectedCategoryName")
            val selectedCategory = mainViewModel.getCategoryByName(selectedCategoryName)

            if (selectedCategory != null) {
                CreateServiceCardScreen(
                    selectedCategory,
                    navController = navHostController,
                    viewModel = mainViewModel
                )
            }
        }

        composable(ScreenRole.Master.EditServiceCard.route) { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId")
            EditServiceCardScreen(
                serviceName = serviceId,
                navController = navHostController,
                viewModel = mainViewModel
            )
        }

//        composable(ScreenRole.Master.EditServiceCard.route) {
//            EditServiceCardScreen(
//                navController = navHostController
//            )
//        }


    }
}