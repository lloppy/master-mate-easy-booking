package com.example.skills

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.skills.navigation.SetupRoleNavGraph
import com.example.skills.ui.theme.SkillsTheme

class MainActivity : ComponentActivity() {
    private lateinit var roleNavController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkillsTheme {
                roleNavController = rememberNavController()
                SetupRoleNavGraph(roleNavController, LocalContext.current)
            }
        }
    }
}

