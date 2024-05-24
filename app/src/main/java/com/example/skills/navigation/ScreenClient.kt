package com.example.skills.navigation

import com.example.skills.R

sealed class ScreenClient(val route: String, val iconId: Int) {
    object ClientMastersScreen :
        ScreenClient(route = "client_masters_screen", iconId = R.drawable.server)

    object ClientBookingsScreen :
        ScreenClient(route = "all_client_bookings_screen", iconId = R.drawable.check_square)


    object ClientSettingsScreen :
        ScreenClient(route = "client_settings_screen", iconId = R.drawable.settings)
}