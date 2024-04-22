package com.example.skills.master.navigation.createService

sealed class ScreenMaterCreateService(val route: String) {
    object InputServiceName : ScreenMaterCreateService(route = "input_service_name_screen")
    object MainCreationLayout : ScreenMaterCreateService(route = "main_creation_layout")

    object MasterCreateServiceScreen : ScreenMaterCreateService(route = "master_create_service_screen")

}