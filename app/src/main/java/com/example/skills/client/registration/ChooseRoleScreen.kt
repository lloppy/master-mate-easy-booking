package com.example.skills.client.registration

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.skills.navigation.client.registration.Screen
import com.example.skills.ui.theme.fontFamilyInter
import com.example.skills.ui.theme.paddingBetweenElements

@Composable
fun ChooseRoleScreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.50f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "logo_image",
                Modifier.fillMaxSize(0.3f),
                alignment = Alignment.Center
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "Добро пожаловать!",
                fontSize = 32.sp,
                fontFamily = fontFamilyInter,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Как мастер, создавайте своё идеальное расписание, предлагайте услуги и управляйте записями клиентов. Как клиет, записывайтесь на услуги к разным специалистам и управляйте своими записями. Легко синхронизируйте свои данные и следите за своим расписанием в удобном Google Календаре.",
                fontSize = 14.sp,
                fontFamily = fontFamilyInter,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            CustomButton(Screen.ClientLogIn.route, navController, "Я клиент", 0.45f)
            Spacer(modifier = Modifier.padding(8.dp))
            CustomButton(
                Screen.ClientLogIn.route,
                navController,
                "Я мастер",
                color = Color.Transparent
            )
        }
    }
}


@Composable
fun CustomButton(
    navResId: String,
    navController: NavController,
    buttonText: String,
    height: Float = 1f,
    color: Color = Color.Black
) {
    Button(
        onClick = { navController.navigate(navResId) },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(height)
            .padding(start = paddingBetweenElements, end = paddingBetweenElements),
        shape = RoundedCornerShape(buttonRoundedCorner),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = if (color == Color.Black) Color.White else Color.Black
        ),
        border = BorderStroke(1.dp, Color.Black)

    ) {
        Text(text = buttonText)
    }

}

const val buttonRoundedCorner = 16


