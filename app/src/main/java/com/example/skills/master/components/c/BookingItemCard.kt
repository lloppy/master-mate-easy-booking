package com.example.skills.master.components.c

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.master.components.d.CustomAlertDialog
import com.example.skills.master.components.e.lineHeight


@Composable
fun BookingItemCard(bookingItem: BookingItem, navController: NavHostController) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        CustomAlertDialog(
            onDismiss = {
                showDialog = false
            },
            onExit = {
                showDialog = false
            })
    }
    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .height(164.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(20.dp))
            .background(Color.White)
    ) {
        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp)) {
            Spacer(modifier = Modifier.height(paddingBetweenText.plus(paddingBetweenText)))
            val timeEnd = bookingItem.timeStart.plusMinutes(bookingItem.duration.toLong())
            Text(
                text = "${String.format("%02d", bookingItem.timeStart.hour)}:${
                    String.format(
                        "%02d",
                        bookingItem.timeStart.minute
                    )
                } - ${String.format("%02d", timeEnd.hour)}:${
                    String.format(
                        "%02d",
                        timeEnd.minute
                    )
                }",
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(paddingBetweenText))
            Text(
                text = bookingItem.serviceName,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(paddingBetweenText))
            Text(
                text = bookingItem.price.toString() + " руб",
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(paddingBetweenText))
            Text(
                text = "Клиент: ${bookingItem.clientName} ${bookingItem.clientAge} лет  ",
                color = Color.LightGray,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                maxLines = 3,
                lineHeight = lineHeight
            )
        }
    }
}


val paddingBetweenText = 9.dp