package com.example.skills.ui.client.a.new_booking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.example.skills.data.viewmodel.route.BookingViewModel
import com.example.skills.ui.components.CustomButton
import com.example.skills.ui.master.b.calendar.ContinuousSelectionHelper.getSelection
import com.example.skills.ui.master.b.calendar.DateSelection
import com.example.skills.ui.master.b.calendar.Day
import com.example.skills.ui.master.b.calendar.DaysOfWeekHeader
import com.example.skills.ui.master.b.calendar.monthNames
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.DayPosition
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun ClientCalendarView(
    bookingViewModel: BookingViewModel,
    navigateToSelectTime: () -> Unit
) {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val startMonth = remember { currentMonth }
    val endMonth = remember { currentMonth.plusMonths(12) }
    val today = remember { LocalDate.now() }

    var selection by remember { mutableStateOf(DateSelection()) }

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                ) {
                    val calendarState = rememberCalendarState(currentMonth)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${monthNames[currentMonth.monthValue - 1]} ${currentMonth.year}".capitalize(),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                        Row {
                            IconButton(onClick = {
                                currentMonth = currentMonth.minusMonths(1)
                            }) {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Назад"
                                )
                            }
                            IconButton(onClick = {
                                currentMonth = currentMonth.plusMonths(1)
                            }) {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = "Вперед"
                                )
                            }
                        }
                    }
                    VerticalCalendar(
                        state = calendarState,
                        dayContent = { value ->
                            Day(
                                value,
                                today = today,
                                selection = selection
                            ) { day ->
                                if (day.position == DayPosition.MonthDate &&
                                    (day.date == today || day.date.isAfter(today))
                                ) {
                                    selection = getSelection(
                                        clickedDate = day.date,
                                        dateSelection = selection,
                                    )
                                }
                            }
                        },
                        monthHeader = { DaysOfWeekHeader() },
                    )
                }
                if (selection.daysBetween != null || selection.startDate != null) {
                    CustomButton(
                        navigateTo = {
                            bookingViewModel.data3 = MutableLiveData(selection.startDate)
                            navigateToSelectTime.invoke()
                        },
                        buttonText = "Далее"
                    )
                }
            }
        }
    }
}
