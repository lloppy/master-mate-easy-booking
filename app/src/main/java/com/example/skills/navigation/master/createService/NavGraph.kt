package com.example.skills.navigation.master.createService

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.master.MainMasterScreen
import com.example.skills.master.creatingService.MainCreationLayout
import com.example.skills.master.creatingService.MasterCreateServiceScreen
import com.example.skills.master.creatingService.steps.InputServiceName

@Composable
fun SetupCreateServiceNavGraph(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenMaterCreateService.InputServiceName.route
    ) {
        composable(route = ScreenMaterCreateService.MainCreationLayout.route) {
            MainCreationLayout()
        }
        composable(route = ScreenMaterCreateService.InputServiceName.route) {
            InputServiceName()
        }

    }
}