package com.example.skills.client.account.navBar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Stable
import com.example.skills.R
import com.example.skills.client.account.navBar.BellColorButton

@Stable
data class Item(
    @DrawableRes val icon: Int,
    var isSelected: Boolean,
    val showNewMessages: Boolean,
    val badgeCount: Int? = null,
    val route: String,
    @StringRes val description: Int,
    val animationType: ColorButtonAnimation = BellColorButton(
        tween(500),
        background = ButtonBackground(R.drawable.plus)
    ),
)


