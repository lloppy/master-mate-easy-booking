package com.example.skills.role

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.skills.client.account.MainClientLayout
import com.example.skills.master.MainMasterLayout
import com.example.skills.master.components.CodeVerificationScreen
import com.example.skills.master.components.DoneClientRegistrationScreen
import com.example.skills.master.components.DoneMasterRegistrationScreen
import com.example.skills.master.components.ForgotPasswordScreen
import com.example.skills.master.components.LogInScreen
import com.example.skills.master.components.NewPasswordScreen
import com.example.skills.master.components.RegistrationScreen

fun NavGraphBuilder.clientNavGraph(navController: NavHostController) {
    navigation(
        startDestination = ScreenRole.Client.LogIn.route,
        route = "client"
    ) {

        composable(ScreenRole.Client.LogIn.route) {
            LogInScreen(
                navController = navController,
                routeLogIn = ScreenRole.Client.MainLayout.route,
                routeSignIn = ScreenRole.Client.Registration.route,
                routeForgotPassword = ScreenRole.Client.ForgotPassword.route
            )
        }

        composable(ScreenRole.Client.Registration.route) {
            RegistrationScreen(navController = navController, ScreenRole.Client.CodeVerification)
        }

        composable(ScreenRole.Client.MainLayout.route) {
            MainClientLayout()
        }

        composable(ScreenRole.Client.ForgotPassword.route) {
            ForgotPasswordScreen(navController = navController, ScreenRole.Client.CodeVerificationNewPassword)
        }

        composable(ScreenRole.Client.CodeVerification.route) {
            CodeVerificationScreen(navController = navController, ScreenRole.Client.DoneRegistration)
        }

        composable(ScreenRole.Client.CodeVerificationNewPassword.route) {
            CodeVerificationScreen(navController = navController, ScreenRole.Client.NewPassword)
        }

        composable(ScreenRole.Client.NewPassword.route) {
            NewPasswordScreen(navController = navController, ScreenRole.Client.MainLayout)
        }

        composable(ScreenRole.Client.DoneRegistration.route) {
            DoneClientRegistrationScreen(navController = navController)
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
                routeSignIn = ScreenRole.Master.Registration.route,
                routeForgotPassword = ScreenRole.Master.ForgotPassword.route
            )
        }

        composable(ScreenRole.Master.Registration.route) {
            RegistrationScreen(navController = navController, ScreenRole.Master.CodeVerification)
        }

        composable(ScreenRole.Master.MainLayout.route) {
            MainMasterLayout()
        }

        composable(ScreenRole.Master.ForgotPassword.route) {
            ForgotPasswordScreen(navController = navController, ScreenRole.Master.CodeVerificationNewPassword)
        }

        composable(ScreenRole.Master.CodeVerification.route) {
            CodeVerificationScreen(navController = navController, ScreenRole.Master.DoneRegistration)
        }

        composable(ScreenRole.Master.CodeVerificationNewPassword.route) {
            CodeVerificationScreen(navController = navController, ScreenRole.Master.NewPassword)
        }

        composable(ScreenRole.Master.NewPassword.route) {
            NewPasswordScreen(navController = navController, ScreenRole.Master.MainLayout)
        }

        composable(ScreenRole.Master.DoneRegistration.route) {
            DoneMasterRegistrationScreen(navController = navController)
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