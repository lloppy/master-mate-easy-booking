package com.example.skills.navigation.master.service_creation

import com.example.skills.R

sealed class ScreenMaterCreation(val route: String, val iconId: Int) {
    object MasterHomeScreen : ScreenMaterCreation(route = "master_home_screen", iconId = R.drawable.home)

    object MasterChatsScreen : ScreenMaterCreation(route = "master_chat_screen", iconId = R.drawable.message_buble)

    object MasterCalendarScreen : ScreenMaterCreation(route = "master_calendar_screen", iconId = R.drawable.person)
    object MasterCreateServiceScreen : ScreenMaterCreation(route = "master_create_service_screen", iconId = R.drawable.plus)



}