package com.example.skills.navigation.client.registration

sealed class Screen(val route: String) {
    object ChooseRole : Screen(route = "choose_role_screen")

    object ClientLogIn : Screen(route = "client_log_in_screen")
    object ClientRegistration : Screen(route = "client_registration_screen")
    object MainClientLayout : Screen(route = "main_client_layout_screen")
    object MainMasterLayout : Screen(route = "main_master_layout_screen")


}