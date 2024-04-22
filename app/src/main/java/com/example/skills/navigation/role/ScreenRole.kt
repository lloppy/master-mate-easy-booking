package com.example.skills.navigation.role

sealed class ScreenRole(val route: String) {
    object MasterLogIn : ScreenRole(route = "master_log_in_screen")
    object ClientLogIn : ScreenRole(route = "client_log_in_screen")
    object RoleLayout : ScreenRole(route = "role_layout_screen")
}