package com.example.skills.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode

val backgroundMaterial = Color(0xFFF9F8F4)
val greenMaterial = Color(0xFFF9F8F4)
val orangeMaterial = Color(0xFFF9F8F4)
val blackMaterial = Color(0xFF111111)
val itemMaterial = Color(0xFFEEEEEA)

val blackTextMaterial = Color(0xFF111111)
val whiteTextMaterial = Color(0xFFFFFFFF)

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val horizontalGradient = Brush.horizontalGradient(
    0.0f to Color.Magenta,
    1f to Color.Cyan,
    startX = 0.0f,
    endX = 1000.0f
)

val linearGradient = Brush.linearGradient(
    0.0f to Color.Magenta,
    500.0f to Color.Cyan,
    start = Offset.Zero,
    end = Offset.Infinite
)

val radialGradient = Brush.radialGradient(
    0.0f to Color.Magenta,
    1.0f to Color.Cyan,
    radius = 1500.0f,
    tileMode = TileMode.Repeated
)

//colors = OutlinedTextFieldDefaults.colors(
//focusedPlaceholderColor = Color.White,
//unfocusedPlaceholderColor = Color.White,
//focusedLabelColor = Color.White,
//unfocusedLabelColor = Color.White,
//focusedBorderColor = Color.White,
//unfocusedBorderColor = Color.White,
//focusedTextColor = Color.White,
//unfocusedTextColor = Color.White,
//cursorColor = Color.White
//),