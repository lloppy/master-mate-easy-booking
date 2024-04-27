package com.example.skills.master.navigation.account

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import com.example.skills.R

sealed class ScreenMater(val route: String, val iconId: Int) {
    object MasterHomeScreen : ScreenMater(route = "master_home_screen", iconId = R.drawable.person)

    object MasterCalendarScreen :
        ScreenMater(route = "master_calendar_screen", iconId = R.drawable.baseline_calendar_today_24)

    object MasterCreateServiceScreen :
        ScreenMater(route = "master_create_service_screen", iconId = R.drawable.check_square)

    object MasterServerScreen :
        ScreenMater(route = "master_service_screen", iconId = R.drawable.server)

    object MasterSettingsScreen :
        ScreenMater(route = "master_settings_screen", iconId = R.drawable.settings)


    object MainCreationLayout :
        ScreenMater(route = "master_create_service_layout", iconId = R.drawable.plus)


}