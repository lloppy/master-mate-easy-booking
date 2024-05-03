package com.example.skills.client.navigation.account

import com.example.skills.R

sealed class ScreenClient(val route: String, val iconId: Int) {
    object ClientHomeScreen : ScreenClient(route = "client_home_screen", iconId = R.drawable.person)

    object ClientCalendarScreen :
        ScreenClient(route = "client_calendar_screen", iconId = R.drawable.baseline_calendar_today_24)

    object ClientCreateServiceScreen :
        ScreenClient(route = "client_create_service_screen", iconId = R.drawable.check_square)

    object ClientServerScreen :
        ScreenClient(route = "client_service_screen", iconId = R.drawable.server)

    object ClientSettingsScreen :
        ScreenClient(route = "client_settings_screen", iconId = R.drawable.settings)


}