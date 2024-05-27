package com.example.skills.ui.client.a.new_booking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.skills.data.viewmodel.route.BookingViewModel
import com.example.skills.ui.components.CustomButton
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectTimeScreen(
    bookingViewModel: BookingViewModel,
    navController: NavHostController,
    navigateToConfirmBooking: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Выберите время",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        SelectTimeContent(
            innerPadding,
            bookingViewModel,
            navigateToConfirmBooking
        )
    }
}


@Composable
fun SelectTimeContent(
    innerPadding: PaddingValues,
    bookingViewModel: BookingViewModel,
    navigateToConfirmBooking: () -> Unit
) {
    val timeSlots = List(24) {
        String.format("%02d:%02d", it / 2, (it % 2) * 30)
    }
    var selectedTime by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = innerPadding
                    .calculateTopPadding()
                    .plus(16.dp), bottom = 100.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp
                ), verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = bookingViewModel.data3.value!!.format(
                    DateTimeFormatter.ofPattern(
                        "d MMMM",
                        Locale("ru")
                    )
                ),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 24.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
            ) {
                items(timeSlots.size) { index ->
                    val isSelected = timeSlots[index] == selectedTime
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) Color.Black else if (alreadyBooking.contains(
                                    timeSlots[index]
                                )
                            ) Color.Gray else Color.LightGray,
                            contentColor = if (isSelected) Color.White else Color.Black
                        ),
                        modifier = Modifier.padding(2.dp),
                        onClick = { selectedTime = timeSlots[index] }
                    ) {
                        Text(
                            text = timeSlots[index],
                        )
                    }
                }
            }
            if (alreadyBooking.contains(selectedTime)) {
                Text(
                    text = "Вы выбрали время, которое уже занято другим клиентом, если он отменит запись, вы получете уведомление и сможете подтвердить свою бронь или отказаться",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.LightGray
                )
            }
        }
        CustomButton(
            navigateTo = {
                val parsedTime = LocalTime.parse(selectedTime, DateTimeFormatter.ofPattern("HH:mm"))
                bookingViewModel.data4 = MutableLiveData(parsedTime)

                navigateToConfirmBooking.invoke()
            },
            buttonText = "Далее",
            enabled = if (selectedTime.isNotEmpty() && !alreadyBooking.contains(selectedTime)) true else false
        )
    }
}

val alreadyBooking = listOf("00:00", "07:30", "10:00")