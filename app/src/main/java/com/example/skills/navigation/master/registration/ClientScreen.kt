package com.example.skills.navigation.master.registration

sealed class MasterScreen(val route: String) {
    object MasterLogIn : MasterScreen(route = "master_log_in_screen")
    object MasterRegistration : MasterScreen(route = "master_registration_screen")
    object MainMasterLayout : MasterScreen(route = "main_master_layout_screen")
}