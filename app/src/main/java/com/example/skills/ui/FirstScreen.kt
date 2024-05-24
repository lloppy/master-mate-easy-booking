package com.example.skills.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.navigation.NavController
import com.example.skills.R
import com.example.skills.navigation.ScreenRole
import com.example.skills.ui.components.CustomButton
import com.example.skills.ui.theme.fontFamilyInter

@Composable
fun RoleScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.52f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "logo_image",
                Modifier.fillMaxSize(0.26f),
                alignment = Alignment.Center
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 8.dp, end = 8.dp, bottom = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "Добро пожаловать!",
                fontSize = 32.sp,
                fontFamily = fontFamilyInter,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Как мастер, создавайте своё идеальное расписание, предлагайте услуги и управляйте записями клиентов. Как клиет, записывайтесь на услуги к разным специалистам и управляйте своими записями. Легко синхронизируйте свои данные и следите за своим расписанием в удобном Google Календаре.",
                fontSize = 14.sp,
                fontFamily = fontFamilyInter,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),

                )
            Spacer(modifier = Modifier.padding(12.dp))

            CustomButton(
                ScreenRole.Client.LogIn.route,
                navController,
                "Я клиент"
            )
            Spacer(modifier = Modifier.padding(2.dp))
            CustomButton(
                ScreenRole.Master.LogIn.route,
                navController,
                "Я мастер",
                color = Color.Transparent
            )
        }

    }
}
