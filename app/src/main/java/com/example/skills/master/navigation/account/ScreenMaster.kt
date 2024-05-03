package com.example.skills.master.navigation.account

import com.example.skills.R

sealed class ScreenMaster(val route: String, val iconId: Int) {
    object MasterHomeScreen : ScreenMaster(route = "master_home_screen", iconId = R.drawable.person)

    object MasterCalendarScreen :
        ScreenMaster(route = "master_calendar_screen", iconId = R.drawable.baseline_calendar_today_24)

    object MasterCreateServiceScreen :
        ScreenMaster(route = "master_create_service_screen", iconId = R.drawable.check_square)

    object MasterServerScreen :
        ScreenMaster(route = "master_service_screen", iconId = R.drawable.server)

    object MasterSettingsScreen :
        ScreenMaster(route = "master_settings_screen", iconId = R.drawable.settings)


}