package com.example.skills.navigation.client.registration

sealed class ClientScreen(val route: String) {
    object ClientLogIn : ClientScreen(route = "client_log_in_screen")
    object ClientRegistration : ClientScreen(route = "client_registration_screen")
    object MainClientLayout : ClientScreen(route = "main_client_layout_screen")
}