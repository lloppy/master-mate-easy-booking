package com.example.skills.navigation

sealed class Screen(val route: String) {
    object LogIn: Screen(route = "log_in_screen")
    object Registration: Screen(route = "registration_screen")
}