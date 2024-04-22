package com.example.skills.master.registration

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.skills.master.components.SignInAlignedTopAppBar
import com.example.skills.role.ScreenRole

@Composable
fun RegistrationMasterScreen(navController: NavHostController) {
    SignInAlignedTopAppBar(navController = navController, ScreenRole.MasterMainLayout.route)

}


