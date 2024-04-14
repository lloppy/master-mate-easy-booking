package com.example.skills.navigation.client.registration

sealed class Screen(val route: String) {
    object LogIn : Screen(route = "log_in_screen")
    object Registration : Screen(route = "registration_screen")
    object MainUserLayout : Screen(route = "main_user_layout_screen")

}