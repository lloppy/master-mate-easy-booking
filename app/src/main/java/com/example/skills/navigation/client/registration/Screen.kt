package com.example.skills.navigation.client.registration

import com.example.skills.R

sealed class Screen(val route: String) {
    object LogIn : Screen(route = "log_in_screen")
    object Registration : Screen(route = "registration_screen")
    object MainLayout : Screen(route = "main_layout_screen")

}