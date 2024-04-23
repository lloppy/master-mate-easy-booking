package com.example.skills.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    headlineMedium = TextStyle(
        fontFamily = fontFamilyInter,
        fontSize = 64.sp,
        color = blackTextMaterial,
        fontWeight = FontWeight.Thin,
        shadow = Shadow(color = Color.LightGray, offset = Offset(1f, 3f), blurRadius = 10f)
    ),
    headlineSmall = TextStyle(
        fontFamily = fontFamilyInter,
        fontSize = 58.sp,
        color = blackTextMaterial,
        fontWeight = FontWeight.Thin,
        shadow = Shadow(color = Color.LightGray, offset = Offset(1f, 3f), blurRadius = 10f)
    ),
    titleSmall = TextStyle(
        fontFamily = fontFamilyInter,
        fontSize = 36.sp,
        color = blackTextMaterial,
        fontWeight = FontWeight.Thin,
        shadow = Shadow(color = Color.LightGray, offset = Offset(1f, 3f), blurRadius = 10f)
    ),
    titleMedium = TextStyle(
        fontFamily = fontFamilyInter,
        fontSize = 24.sp,
        color = blackTextMaterial,
    ),
    labelMedium = TextStyle(
        fontFamily = fontFamilyInter,
        fontSize = 24.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = fontFamilyInter,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val paddingBetweenElements = 8.dp
val spacer = 8.dp

