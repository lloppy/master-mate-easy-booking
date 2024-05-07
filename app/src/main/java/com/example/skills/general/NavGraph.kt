package com.example.skills.general

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.skills.client.MainClientLayout
import com.example.skills.general.components.CodeVerificationScreen
import com.example.skills.general.components.DoneClientRegistrationScreen
import com.example.skills.general.components.DoneMasterInfoRegistrationScreen
import com.example.skills.general.components.DoneMasterRegistrationScreen
import com.example.skills.general.components.ForgotPasswordScreen
import com.example.skills.general.components.FullProfileScreen
import com.example.skills.general.components.LogInScreen
import com.example.skills.general.components.NewPasswordScreen
import com.example.skills.general.components.RegistrationScreen
import com.example.skills.master.MainMasterLayout

fun NavGraphBuilder.clientNavGraph(navController: NavHostController) {
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
                }
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
                }
            )
        }

        composable(ScreenRole.Client.VerificationEmailForNewPassword.route) {
            CodeVerificationScreen(
                navController = navController,
                navigateToCreateNewPassword = {
                    navController.navigate(ScreenRole.Client.CreateNewPassword.route)
                }
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
                },
            )
        }

    }
}

fun NavGraphBuilder.masterNavGraph(navController: NavHostController) {
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
                }
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
                }
            )
        }

        composable(ScreenRole.Master.VerificationEmailForNewPassword.route) {
            CodeVerificationScreen(navController = navController,
                navigateToCreateNewPassword = {
                    navController.navigate(ScreenRole.Master.CreateNewPassword.route)
                }
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
                }
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
fun SetupRoleNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = ScreenRole.RoleLayout.route) {

        // Стартовая страница
        composable(ScreenRole.RoleLayout.route) {
            RoleScreen(navController = navHostController)
        }

        clientNavGraph(navController = navHostController)
        masterNavGraph(navController = navHostController)
    }
}