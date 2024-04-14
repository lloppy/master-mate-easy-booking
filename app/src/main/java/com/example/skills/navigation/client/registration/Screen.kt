package com.example.skills.navigation.client.registration

sealed class Screen(val route: String) {
    object LogIn : Screen(route = "log_in_screen")
    object Registration : Screen(route = "registration_screen")
    object MainClientLayout : Screen(route = "main_client_layout_screen")
    object MainMasterLayout : Screen(route = "main_master_layout_screen")

}