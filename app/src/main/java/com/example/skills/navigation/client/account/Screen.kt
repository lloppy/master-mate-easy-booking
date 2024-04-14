package com.example.skills.navigation.client.account

import com.example.skills.R

sealed class Screen(val route: String, val iconId: Int) {
    object UserHomeScreen : Screen(route = "user_home_screen", iconId = R.drawable.home)

    object ChatsScreen : Screen(route = "chat_screen", iconId = R.drawable.message_buble)

    object CalendarScreen : Screen(route = "calendar_screen", iconId = R.drawable.person)

}