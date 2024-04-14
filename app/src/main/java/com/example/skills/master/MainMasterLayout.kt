package com.example.skills.master

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.skills.master.navBar.MasterBottomNavigation
import com.example.skills.navigation.master.account.SetupMasterNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainMasterLayout() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { MasterBottomNavigation(navController = navController) }
    ) {
        SetupMasterNavGraph(navHostController = navController)
    }
}