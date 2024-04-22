package com.example.skills.client.account

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.skills.client.account.navBar.ClientBottomNavigation
import com.example.skills.client.navigation.account.SetupClientNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainClientLayout() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { ClientBottomNavigation(navController = navController) }
    ) {
        SetupClientNavGraph(navHostController = navController)
    }
}