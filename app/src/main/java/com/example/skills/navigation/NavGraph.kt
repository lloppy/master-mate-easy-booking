package com.example.skills.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

fun NavGraphBuilder.clientNavGraph(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    navigation(
        startDestination = ScreenRole.Client.LogIn.route,
        route = "client"
    ) {
        composable(ScreenRole.Client.LogIn.route) {
            LogInScreen(
                navController = navHostController,
                routeLogIn = ScreenRole.Client.MainLayout.route,
                navigateToRegistration = {
                    navHostController.navigate(ScreenRole.Client.Registration.route)
                },
                navigateToForgotPassword = {
                    navHostController.navigate(ScreenRole.Client.ForgotPassword.route)
                },
                navigateToMain = {
                    navHostController.navigate(ScreenRole.Client.MainLayout.route)
                },
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Client.Registration.route) {
            RegistrationScreen(
                navController = navHostController, // используется только для стрелки Назад
                navigateToCodeVerification = {
                    navHostController.navigate(ScreenRole.Client.CodeVerification.route)
                },
                viewModel = mainViewModel,
                isClient = true
            )
        }

        composable(ScreenRole.Client.MainLayout.route) {
            MainClientLayout(
                navController = rememberNavController(),
                mainViewmodel = mainViewModel
            )
        }

        composable(ScreenRole.Client.ForgotPassword.route) {
            ForgotPasswordScreen(
                navController = navHostController,
                navigateToCodeVerification = {
                    navHostController.navigate(ScreenRole.Client.VerificationEmailForNewPassword.route)
                }
            )
        }

        composable(ScreenRole.Client.CodeVerification.route) {
            CodeVerificationScreen(
                navController = navHostController,
                navigateToDoneRegistration = {
                    navHostController.navigate(ScreenRole.Client.DoneRegistration.route)
                },
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Client.VerificationEmailForNewPassword.route) {
            CodeVerificationScreen(
                navController = navHostController,
                navigateToCreateNewPassword = {
                    navHostController.navigate(ScreenRole.Client.CreateNewPassword.route)
                },
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Client.CreateNewPassword.route) {
            NewPasswordScreen(
                navController = navHostController,
                navigateToMain = {
                    navHostController.navigate(ScreenRole.Client.MainLayout.route)
                }
            )
        }

        composable(ScreenRole.Client.DoneRegistration.route) {
            DoneClientRegistrationScreen(
                navigateToMain = {
                    navHostController.navigate(ScreenRole.Client.MainLayout.route)
                },
                navigateToSetUpCalendar = {
                    //TODO()
                }
            )
        }

    }
}

fun NavGraphBuilder.masterNavGraph(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    navigation(
        startDestination = ScreenRole.Master.MainLayout.route,
        route = "master"
    ) {
        composable(ScreenRole.Master.LogIn.route) {
            LogInScreen(
                navController = navHostController,
                routeLogIn = ScreenRole.Master.MainLayout.route,
                navigateToRegistration = {
                    navHostController.navigate(ScreenRole.Master.Registration.route)
                },
                navigateToForgotPassword = {
                    navHostController.navigate(ScreenRole.Master.ForgotPassword.route)
                },
                navigateToMain = {
                    navHostController.navigate(ScreenRole.Master.MainLayout.route)
                },
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Master.Registration.route) {
            RegistrationScreen(
                navController = navHostController,
                navigateToCodeVerification = {
                    navHostController.navigate(ScreenRole.Master.CodeVerification.route)
                },
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Master.MainLayout.route) {
            MainMasterLayout(
                navController = rememberNavController(),
                mainViewmodel = mainViewModel
            )
        }

        composable(ScreenRole.Master.ForgotPassword.route) {
            ForgotPasswordScreen(
                navController = navHostController,
                navigateToCodeVerification = {
                    navHostController.navigate(ScreenRole.Master.VerificationEmailForNewPassword.route)
                }
            )
        }

        composable(ScreenRole.Master.CodeVerification.route) {
            CodeVerificationScreen(
                navController = navHostController,
                navigateToDoneRegistration = {
                    navHostController.navigate(ScreenRole.Master.DoneRegistration.route)
                },
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Master.VerificationEmailForNewPassword.route) {
            CodeVerificationScreen(
                navController = navHostController,
                navigateToCreateNewPassword = {
                    navHostController.navigate(ScreenRole.Master.CreateNewPassword.route)
                },
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Master.CreateNewPassword.route) {
            NewPasswordScreen(
                navController = navHostController,
                navigateToMain = {
                    navHostController.navigate(ScreenRole.Master.MainLayout.route)
                }
            )
        }

        composable(ScreenRole.Master.DoneRegistration.route) {
            DoneMasterRegistrationScreen(
                navigateToFullProfile = {
                    navHostController.navigate(ScreenRole.Master.FullProfile.route)
                }
            )
        }

        composable(ScreenRole.Master.FullProfile.route) {
            FullProfileScreen(
                navController = navHostController,
                navigateToDoneRegistration = {
                    navHostController.navigate(ScreenRole.Master.DoneMasterInfoRegistration.route)
                },
                viewModel = mainViewModel
            )
        }

        composable(ScreenRole.Master.DoneMasterInfoRegistration.route) {
            DoneMasterInfoRegistrationScreen(
                navigateToMain = {
                    navHostController.navigate(ScreenRole.Master.MainLayout.route)
                }
            )
        }
    }
}

@Composable
fun SetupRoleNavGraph(navHostController: NavHostController, mainViewModel: MainViewModel) {
    NavHost(navController = navHostController, startDestination = ScreenRole.RoleLayout.route) {
        clientNavGraph(navHostController = navHostController, mainViewModel)
        masterNavGraph(navHostController = navHostController, mainViewModel)


        if (mainViewModel.currentUser != null) {
            val role = mainViewModel.userRole
            if (role?.toLowerCase() == "master") {
                navHostController.navigate(ScreenRole.Master.LogIn.route) {
                    popUpTo(ScreenRole.RoleLayout.route) { inclusive = true }

                }
            } else if (role?.toLowerCase() == "client") {
                navHostController.navigate(ScreenRole.Client.LogIn.route) {
                    popUpTo(ScreenRole.RoleLayout.route) { inclusive = true }
                }
            }
        } else {
            composable(ScreenRole.RoleLayout.route) {
                RoleScreen(
                    navigateToClientLogin = {
                        navHostController.navigate(ScreenRole.Client.LogIn.route)
                    },
                    navigateToMasterLogin = {
                        navHostController.navigate(ScreenRole.Master.LogIn.route)
                    },
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}