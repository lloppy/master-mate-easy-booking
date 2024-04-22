package com.example.skills.client.navigation.account

import com.example.skills.R

sealed class ScreenClient(val route: String, val iconId: Int) {
    object ClientHomeScreen : ScreenClient(route = "client_home_screen", iconId = R.drawable.home)

    object ClientChatsScreen : ScreenClient(route = "client_chat_screen", iconId = R.drawable.message_buble)

    object ClientCalendarScreen : ScreenClient(route = "client_calendar_screen", iconId = R.drawable.person)

}