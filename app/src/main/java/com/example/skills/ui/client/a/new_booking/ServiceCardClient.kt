package com.example.skills.ui.client.a.new_booking

import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.example.skills.data.api.MasterForClient
import com.example.skills.data.entity.Service
import com.example.skills.data.viewmodel.route.BookingViewModel
import com.example.skills.ui.master.b.calendar.clickable

@Composable
fun ServiceCardClient(
    singleService: Service,
    navigateToSelectDate: () -> Unit,
    user: MasterForClient,
    bookingViewModel: BookingViewModel
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 4.dp, end = 4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Black)
            .clickable {
                bookingViewModel.data2 = MutableLiveData(singleService)
                navigateToSelectDate.invoke()
            },
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
                text = singleService.duration.toString() + " мин",
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
            } else {
                Spacer(modifier = Modifier.height(paddingBetweenText))
                Text(
                    text = "Адрес: " + if (user.address?.city != null) user.address?.city else " — " ,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

val paddingBetweenText = 7.dp