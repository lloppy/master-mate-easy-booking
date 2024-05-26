package com.example.skills.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.ui.RoleScreen
import com.example.skills.ui.client.MainClientLayout
import com.example.skills.ui.components.CodeVerificationScreen
import com.example.skills.ui.components.DoneClientRegistrationScreen
import com.example.skills.ui.components.DoneMasterInfoRegistrationScreen
import com.example.skills.ui.components.DoneMasterRegistrationScreen
import com.example.skills.ui.components.ForgotPasswordScreen
import com.example.skills.ui.components.FullProfileScreen
import com.example.skills.ui.components.LogInScreen
import com.example.skills.ui.components.NewPasswordScreen
import com.example.skills.ui.components.RegistrationScreen
import com.example.skills.ui.master.MainMasterLayout

fun NavGraphBuilder.clientNavGraph(navController: NavHostController, mainViewModel: MainViewModel) {
    navigation(
        startDestination = ScreenRole.Client.LogIn.route,
        route = "client"
    ) {
        composable(ScreenRole.Client.LogIn.route) {
            LogInScreen(
                navController = navController,
                routeLogIn = ScreenRole.Client.MainLayout.route,
                navigateToRegistration = {
                    navController.navigate(ScreenRole.Client.Registration.route)
                },
                navigateToForgotPassword = {
                    navController.navigate(ScreenRole.Client.ForgotPassword.route)
                },
                navigateToMain = {
                    navController.navigate(ScreenRole.Client.MainLayout.route)
                }
            )
        }

        composable(ScreenRole.Client.Registration.route) {
            RegistrationScreen(
                navController = navController, // используется только для стрелки Назад
                navigateToCodeVerification = {
                    navController.navigate(ScreenRole.Client.CodeVerification.route)
                },
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Client.MainLayout.route) {
            MainClientLayout()
        }

        composable(ScreenRole.Client.ForgotPassword.route) {
            ForgotPasswordScreen(
                navController = navController,
                navigateToCodeVerification = {
                    navController.navigate(ScreenRole.Client.VerificationEmailForNewPassword.route)
                }
            )
        }

        composable(ScreenRole.Client.CodeVerification.route) {
            CodeVerificationScreen(
                navController = navController,
                navigateToDoneRegistration = {
                    navController.navigate(ScreenRole.Client.DoneRegistration.route)
                },
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Client.VerificationEmailForNewPassword.route) {
            CodeVerificationScreen(
                navController = navController,
                navigateToCreateNewPassword = {
                    navController.navigate(ScreenRole.Client.CreateNewPassword.route)
                },
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Client.CreateNewPassword.route) {
            NewPasswordScreen(
                navController = navController,
                navigateToMain = {
                    navController.navigate(ScreenRole.Client.MainLayout.route)
                }
            )
        }

        composable(ScreenRole.Client.DoneRegistration.route) {
            DoneClientRegistrationScreen(
                navigateToMain = {
                    navController.navigate(ScreenRole.Client.MainLayout.route)
                },
                navigateToSetUpCalendar = {
                    //TODO()
                }
            )
        }

    }
}

fun NavGraphBuilder.masterNavGraph(navController: NavHostController, mainViewModel: MainViewModel) {
    navigation(
        startDestination = ScreenRole.Master.LogIn.route,
        route = "master"
    ) {
        composable(ScreenRole.Master.LogIn.route) {
            LogInScreen(
                navController = navController,
                routeLogIn = ScreenRole.Master.MainLayout.route,
                navigateToRegistration = {
                    navController.navigate(ScreenRole.Master.Registration.route)
                },
                navigateToForgotPassword = {
                    navController.navigate(ScreenRole.Master.ForgotPassword.route)
                },
                navigateToMain = {
                    navController.navigate(ScreenRole.Master.MainLayout.route)
                }
            )
        }

        composable(ScreenRole.Master.Registration.route) {
            RegistrationScreen(
                navController = navController,
                navigateToCodeVerification = {
                    navController.navigate(ScreenRole.Master.CodeVerification.route)
                },
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Master.MainLayout.route) {
            MainMasterLayout()
        }

        composable(ScreenRole.Master.ForgotPassword.route) {
            ForgotPasswordScreen(
                navController = navController,
                navigateToCodeVerification = {
                    navController.navigate(ScreenRole.Master.VerificationEmailForNewPassword.route)
                }
            )
        }

        composable(ScreenRole.Master.CodeVerification.route) {
            CodeVerificationScreen(
                navController = navController,
                navigateToDoneRegistration = {
                    navController.navigate(ScreenRole.Master.DoneRegistration.route)
                },
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Master.VerificationEmailForNewPassword.route) {
            CodeVerificationScreen(
                navController = navController,
                navigateToCreateNewPassword = {
                    navController.navigate(ScreenRole.Master.CreateNewPassword.route)
                },
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Master.CreateNewPassword.route) {
            NewPasswordScreen(
                navController = navController,
                navigateToMain = {
                    navController.navigate(ScreenRole.Master.MainLayout.route)
                }
            )
        }

        composable(ScreenRole.Master.DoneRegistration.route) {
            DoneMasterRegistrationScreen(
                navigateToFullProfile = {
                    navController.navigate(ScreenRole.Master.FullProfile.route)
                }
            )
        }

        composable(ScreenRole.Master.FullProfile.route) {
            FullProfileScreen(
                navController = navController,
                navigateToDoneRegistration = {
                    navController.navigate(ScreenRole.Master.DoneMasterInfoRegistration.route)
                },
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Master.DoneMasterInfoRegistration.route) {
            DoneMasterInfoRegistrationScreen(
                navigateToMain = {
                    navController.navigate(ScreenRole.Master.MainLayout.route)
                },
                navigateToSetUpCalendar = {
                    //TODO()
                }
            )
        }
    }
}

@Composable
fun SetupRoleNavGraph(navHostController: NavHostController, context: Context) {
    NavHost(navController = navHostController, startDestination = ScreenRole.RoleLayout.route) {
        val mainViewModel = MainViewModel(context = context)

        // Стартовая страница
        composable(ScreenRole.RoleLayout.route) {
            RoleScreen(navController = navHostController)
        }

        clientNavGraph(navController = navHostController, mainViewModel)
        masterNavGraph(navController = navHostController, mainViewModel)
    }
}