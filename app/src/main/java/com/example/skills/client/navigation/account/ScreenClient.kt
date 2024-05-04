package com.example.skills.client.navigation.account

import com.example.skills.R

sealed class ScreenClient(val route: String, val iconId: Int) {
    object ClientMastersScreen :
        ScreenClient(route = "client_masters_screen", iconId = R.drawable.server)

    object ClientBookingsScreen :
        ScreenClient(route = "client_bookings_screen", iconId = R.drawable.check_square)


    object ClientSettingsScreen :
        ScreenClient(route = "client_settings_screen", iconId = R.drawable.settings)
}