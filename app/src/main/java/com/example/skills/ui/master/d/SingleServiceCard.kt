package com.example.skills.ui.master.d

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.R
import com.example.skills.data.entity.Service
import com.example.skills.navigation.ScreenRole
import com.example.skills.ui.components.CustomButton
import com.example.skills.ui.master.b.calendar.clickable


@Composable
fun SingleServiceCard(singleService: Service, navController: NavHostController) {
    var showDialog by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    if (showDialog) {
        CustomAlertDialog(
            onDismiss = {
                showDialog = false
            },
            onExit = {
                showDialog = false
            },
            "Удалить услугу",
            "Услуга будет удалена навсегда без возможности восстановления"
        )
    }
    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Black),
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = singleService.name.capitalize(),
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(paddingBetweenText))
            Text(
                text = singleService.price.toString() + " руб",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(paddingBetweenText))
            Text(
                text = if (singleService.duration.hours == 0) singleService.duration.minutes.toString() + " мин"
                else singleService.duration.hours.toString() + " ч" + " " + if (singleService.duration.minutes != 0) singleService.duration.minutes.toString() + " мин" else "",
                color = Color.White,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(paddingBetweenText))
            Text(
                text = "Описание: " + singleService.description,
                color = Color.LightGray,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                maxLines = if (expanded) Int.MAX_VALUE else 3,
                lineHeight = 17.sp
            )
            if (!expanded) {
                Text(
                    text = "... ещё",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable { expanded = !expanded })
            }
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    CustomButton(navigateTo = {
                        try {
                            val serviceId = singleService.name
                            navController.navigate(
                                ScreenRole.Master.EditServiceCard.route.replace(
                                    "{serviceId}",
                                    serviceId
                                )
                            )
                        } catch (e: IllegalArgumentException) { // нужно блин выбрать категорию, а не тыкать в пустоту
                        }
                    }, buttonText = "Изменить", width = 0.8f, heightCoeff = 0.8f)
                }
                Box(
                    modifier = Modifier.border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
                ) {
                    IconButton(onClick = { showDialog = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bin),
                            contentDescription = "bin",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

val paddingBetweenText = 7.dp