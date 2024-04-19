package com.example.skills.master.creatingService

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.skills.navigation.master.createService.SetupCreateServiceNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainCreationLayout() {
    val navController = rememberNavController()
    Scaffold {
        SetupCreateServiceNavGraph(navHostController = navController)
    }
}