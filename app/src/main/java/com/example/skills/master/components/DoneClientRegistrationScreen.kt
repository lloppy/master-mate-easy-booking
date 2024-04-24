package com.example.skills.master.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.R
import com.example.skills.role.ScreenRole
import com.example.skills.ui.theme.fontFamilyInter

@Composable
fun DoneClientRegistrationScreen(navController: NavHostController) {
    Log.i("routing_info", "DoneClientRegistrationScreen")

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.50f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = painterResource(R.drawable.ic_done_bubble),
                contentDescription = "logo_image",
                Modifier.fillMaxSize(0.5f),
                alignment = Alignment.Center
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Успешно!",
                fontSize = 28.sp,
                fontFamily = fontFamilyInter,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Вы успешно зарегистрировались в нашем приложении как клиент. Теперь давайте настроим подключение к Google Календарю и уведомления через Telegram.",
                fontSize = 14.sp,
                fontFamily = fontFamilyInter,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomButton(
                ScreenRole.Client.MainLayout.route,
                navController,
                "В другой раз",
                color = Color.Transparent,
                width = 0.5f,
                height = 0.4f
            )

            CustomButton(
                ScreenRole.Client.MainLayout.route,
                navController,
                "Продолжить",
                height = 0.4f
            )
        }
    }
}