package com.example.skills.role

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.client.account.MainClientLayout
import com.example.skills.master.MainMasterLayout
import com.example.skills.master.components.CodeVerificationScreen
import com.example.skills.master.components.DoneClientRegistrationScreen
import com.example.skills.master.components.DoneMasterRegistrationScreen
import com.example.skills.master.components.ForgotPasswordScreen
import com.example.skills.master.components.LogInScreen
import com.example.skills.master.components.NewPasswordScreen
import com.example.skills.master.components.RegistrationScreen

@Composable
fun SetupRoleNavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = ScreenRole.RoleLayout.route) {
        // Стартовая страница
        composable(route = ScreenRole.RoleLayout.route) {
            RolesScreen(navController = navHostController)
        }

        // Вход
        composable(route = ScreenRole.ClientLogIn.route) {
            LogInScreen(
                navController = navHostController,
                routeLogIn = ScreenRole.ClientMainLayout.route,
                routeSignIn = ScreenRole.ClientRegistration.route,
                routeForgotPassword = ScreenRole.ForgotPasswordClient.route
            )
        }
        composable(route = ScreenRole.MasterLogIn.route) {
            LogInScreen(
                navController = navHostController,
                routeLogIn = ScreenRole.MasterMainLayout.route,
                routeSignIn = ScreenRole.MasterRegistration.route,
                routeForgotPassword = ScreenRole.ForgotPasswordMaster.route
            )
        }

        // Регистрация
        composable(route = ScreenRole.ClientRegistration.route) {
            RegistrationScreen(navController = navHostController, ScreenRole.CodeVerificationClient)
        }
        composable(route = ScreenRole.MasterRegistration.route) {
            RegistrationScreen(navController = navHostController, ScreenRole.CodeVerificationMaster)
        }

        // Главные рабочие страницы клиента и мастера
        composable(route = ScreenRole.ClientMainLayout.route) {
            MainClientLayout()
        }
        composable(route = ScreenRole.MasterMainLayout.route) {
            MainMasterLayout()
        }

        // Востановление пароля
        composable(route = ScreenRole.ForgotPasswordClient.route) {
            ForgotPasswordScreen(navController = navHostController, ScreenRole.CodeVerificationNewPasswordClient)
        }
        composable(route = ScreenRole.ForgotPasswordMaster.route) {
            ForgotPasswordScreen(navController = navHostController, ScreenRole.CodeVerificationNewPasswordMaster)
        }

        // Подтвердите Email: регистрация (2 шт - клиент/мастер)
        composable(route = ScreenRole.CodeVerificationClient.route) {
            CodeVerificationScreen(navController = navHostController, ScreenRole.DoneClientRegistration)
        }
        composable(route = ScreenRole.CodeVerificationMaster.route) {
            CodeVerificationScreen(navController = navHostController, ScreenRole.DoneMasterRegistration)
        }


        // Подтвердите Email: для смены пароля (2 шт - клиент/мастер)
        composable(route = ScreenRole.CodeVerificationNewPasswordClient.route) {
            CodeVerificationScreen(navController = navHostController, ScreenRole.NewPasswordClient)
        }
        composable(route = ScreenRole.CodeVerificationNewPasswordMaster.route) {
            CodeVerificationScreen(navController = navHostController, ScreenRole.NewPasswordMaster)
        }

        // Новый пароль
        composable(route = ScreenRole.NewPasswordClient.route) {
            NewPasswordScreen(navController = navHostController, ScreenRole.ClientMainLayout)
        }
        composable(route = ScreenRole.NewPasswordMaster.route) {
            NewPasswordScreen(navController = navHostController, ScreenRole.MasterMainLayout)
        }

        // Окно Успешно!
        composable(route = ScreenRole.DoneClientRegistration.route) {
            DoneClientRegistrationScreen(navController = navHostController)
        }
        composable(route = ScreenRole.DoneMasterRegistration.route) {
            DoneMasterRegistrationScreen(navController = navHostController)
        }



    }
}
