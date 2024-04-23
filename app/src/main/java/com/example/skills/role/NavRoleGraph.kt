package com.example.skills.role

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.client.account.MainClientLayout
import com.example.skills.master.MainMasterLayout
import com.example.skills.master.components.DoneClientRegistrationScreen
import com.example.skills.master.components.DoneMasterRegistrationScreen
import com.example.skills.master.components.ForgotPasswordScreen
import com.example.skills.master.components.LogInScreen
import com.example.skills.master.components.RegistrationScreen

@Composable
fun SetupRoleNavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = ScreenRole.RoleLayout.route) {
        composable(route = ScreenRole.RoleLayout.route) {
            RolesScreen(navController = navHostController)
        }
        composable(route = ScreenRole.ClientLogIn.route) {
            LogInScreen(
                navController = navHostController,
                routeLogIn = ScreenRole.ClientMainLayout.route,
                routeSignIn = ScreenRole.ClientRegistration.route,
                routeForgotPassword = ScreenRole.ForgotPassword.route // разделить на клиента и мастера
            )
        }
        composable(route = ScreenRole.ClientRegistration.route) {
            RegistrationScreen(navController = navHostController, ScreenRole.DoneClientRegistration)
        }
        composable(route = ScreenRole.ClientMainLayout.route) {
            MainClientLayout()
        }


        composable(route = ScreenRole.MasterLogIn.route) {
            LogInScreen(
                navController = navHostController,
                routeLogIn = ScreenRole.MasterMainLayout.route,
                routeSignIn = ScreenRole.MasterRegistration.route,
                routeForgotPassword = ScreenRole.ForgotPassword.route // разделить на клиента и мастера
            )
        }
        composable(route = ScreenRole.MasterRegistration.route) {
            RegistrationScreen(navController = navHostController, ScreenRole.DoneMasterRegistration)
        }
        composable(route = ScreenRole.MasterMainLayout.route) {
            MainMasterLayout()
        }

        composable(route = ScreenRole.DoneClientRegistration.route) {
            DoneClientRegistrationScreen(navController = navHostController)
        }
        composable(route = ScreenRole.DoneMasterRegistration.route) {
            DoneMasterRegistrationScreen(navController = navHostController)
        }

        composable(route = ScreenRole.ForgotPassword.route) {
            ForgotPasswordScreen(navController = navHostController)
        }
    }
}
