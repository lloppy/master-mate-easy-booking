package com.example.skills.navigation.role

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.skills.navigation.client.registration.SetupClientNavGraph
import com.example.skills.navigation.client.registration.SetupMasterNavGraph
import com.example.skills.navigation.role.SetupRoleNavGraph
import com.example.skills.ui.theme.SkillsTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkillsTheme {
                navController = rememberNavController()
//                SetupClientNavGraph(navHostController = navController)
//                SetupMasterNavGraph(navHostController = navController)
                SetupRoleNavGraph(navHostController = navController)

            }
        }
    }
}

