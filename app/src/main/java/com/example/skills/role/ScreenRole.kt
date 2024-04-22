package com.example.skills.role

sealed class ScreenRole(val route: String) {
    object RoleLayout : ScreenRole(route = "role_layout_screen")

    object ClientLogIn : ScreenRole(route = "client_log_in_screen")
    object ClientRegistration : ScreenRole(route = "client_registration_screen")
    object ClientMainLayout : ScreenRole(route = "client_main_layout_screen")


    object MasterLogIn : ScreenRole(route = "master_log_in_screen")
    object MasterRegistration : ScreenRole(route = "master_registration_screen")
    object MasterMainLayout : ScreenRole(route = "master_main_layout_screen")
}