package com.example.skills.navigation.role

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.skills.master.navBar.MasterBottomNavigation
import com.example.skills.navigation.client.registration.SetupClientNavGraph
import com.example.skills.navigation.master.registration.SetupMasterNavGraph
import com.example.skills.ui.theme.SkillsTheme

class MainActivity : ComponentActivity() {
    private lateinit var clientNavController: NavHostController
    private lateinit var masterNavController: NavHostController
    private lateinit var roleNavController: NavHostController
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkillsTheme {
                Scaffold(
                    topBar = {}
                ) {
                    clientNavController = rememberNavController()
                    masterNavController = rememberNavController()
                    roleNavController = rememberNavController()

                    SetupClientNavGraph(navHostController = clientNavController)
                    SetupMasterNavGraph(navHostController = masterNavController)
                    SetupRoleNavGraph(navHostController = roleNavController)
                }
            }
        }
    }
}

