package com.example.skills.master.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.skills.master.components.LogInAlignedTopAppBar
import com.example.skills.role.ScreenRole
import com.example.skills.ui.theme.backgroundMaterial


@Composable
fun LogInMasterScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundMaterial),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LogInAlignedTopAppBar(
            navController,
            routeLogIn = ScreenRole.MasterMainLayout.route,
            routeSignIn = ScreenRole.MasterRegistration.route
        )
    }
}
