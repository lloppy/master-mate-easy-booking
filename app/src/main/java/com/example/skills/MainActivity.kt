package com.example.skills

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.skills.data.api.LogInRequest
import com.example.skills.data.viewmodel.MY_LOG
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.navigation.SetupRoleNavGraph
import com.example.skills.ui.theme.SkillsTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SkillsTheme {
                val mainViewModel = MainViewModel(context = LocalContext.current)
                navController = rememberNavController()

                SetupRoleNavGraph(navController, mainViewModel)
            }
        }
    }
    fun restartApp() {
        val intent = Intent(this, MainActivity::class.java)
        ContextCompat.startActivity(this, intent, null)
        finish()
        Runtime.getRuntime().exit(0)
    }
}
