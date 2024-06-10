package com.example.skills.ui.client.a.new_booking

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.example.skills.R
import com.example.skills.data.entity.Schedule
import com.example.skills.data.viewmodel.MY_LOG
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.data.viewmodel.route.BookingViewModel
import com.example.skills.ui.components.CustomButton
import com.example.skills.ui.master.b.calendar.ContinuousSelectionHelper
import com.example.skills.ui.master.b.calendar.ContinuousSelectionHelper.getSelection
import com.example.skills.ui.master.b.calendar.DateSelection
import com.example.skills.ui.master.b.calendar.DaysOfWeekHeader
import com.example.skills.ui.master.b.calendar.HalfSizeShape
import com.example.skills.ui.master.b.calendar.clickable
import com.example.skills.ui.master.b.calendar.continuousSelectionColor
import com.example.skills.ui.master.b.calendar.monthNames
import com.example.skills.ui.master.b.calendar.selectionColor
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun ClientCalendarView(
    bookingViewModel: BookingViewModel,
    navigateToSelectTime: () -> Unit,
    mainViewModel: MainViewModel
) {
    val schedules by mainViewModel.schedulesLiveData.observeAsState(emptyList())
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val today = remember { LocalDate.now() }
    var selection by remember { mutableStateOf(DateSelection()) }

    val isStartDateScheduled = selection.startDate?.let { startDate ->
        schedules.any {
            LocalDate.parse(it.date) == startDate
        }
    } ?: false

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
                                selection = selection,
                                schedules = schedules
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
                            Log.i(MY_LOG, "date is " + selection.startDate!!.toString())
                            val formatter = DateTimeFormatter.ISO_LOCAL_DATE
                            try {
                                mainViewModel.getFreeTimeSlots(
                                    masterId = bookingViewModel.data1.value!!.masterId!!,
                                    serviceId = bookingViewModel.data2.value!!.id,
                                    date = selection.startDate!!.format(formatter)
                                ) {
                                    Log.e(MY_LOG, "getSchedulesById is successful")
                                    bookingViewModel.data3 = MutableLiveData(selection.startDate)
                                    navigateToSelectTime.invoke()
                                }
                            } catch (_: Exception) { }
                        },
                        buttonText = "Далее",
                        enabled = isStartDateScheduled && selection.endDate == null
                    )
                }
            }
        }
    }
}

@Composable
fun Day(
    day: CalendarDay,
    today: LocalDate,
    selection: DateSelection,
    schedules: List<Schedule>,
    onClick: (CalendarDay) -> Unit
) {
    var textColor = Color.Transparent
    val isScheduled = schedules.any {
        val scheduleDate = LocalDate.parse(it.date)
        scheduleDate == day.date
    }

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable(
                enabled = day.position == DayPosition.MonthDate && day.date >= today,
                showRipple = false,
                onClick = {
                    onClick(day)
                },
            )
            .backgroundHighlight(
                day = day,
                today = today,
                selection = selection,
                selectionColor = selectionColor,
                continuousSelectionColor = continuousSelectionColor,
                isScheduled = isScheduled
            ) { textColor = it },

        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}


fun Modifier.backgroundHighlight(
    day: CalendarDay,
    today: LocalDate,
    selection: DateSelection,
    selectionColor: Color,
    continuousSelectionColor: Color,
    isScheduled: Boolean,
    textColor: (Color) -> Unit,
): Modifier = composed {
    val (startDate, endDate) = selection
    val padding = 4.dp
    when (day.position) {
        DayPosition.MonthDate -> {
            when {
                day.date.isBefore(today) -> {
                    textColor(Color.Gray)
                    this
                }

                startDate == day.date && endDate == null -> {
                    textColor(Color.White)
                    padding(padding)
                        .background(color = selectionColor, shape = CircleShape)
                }

                day.date == startDate -> {
                    textColor(Color.White)
                    padding(vertical = padding)
                        .background(
                            color = continuousSelectionColor,
                            shape = HalfSizeShape(clipStart = true),
                        )
                        .padding(horizontal = padding)
                        .background(color = selectionColor, shape = CircleShape)
                }

                startDate != null && endDate != null && (day.date > startDate && day.date < endDate) -> {
                    textColor(Color.Black)
                    padding(vertical = padding)
                        .background(color = continuousSelectionColor)
                }

                day.date == endDate -> {
                    textColor(Color.White)
                    padding(vertical = padding)
                        .background(
                            color = continuousSelectionColor,
                            shape = HalfSizeShape(clipStart = false),
                        )
                        .padding(horizontal = padding)
                        .background(color = selectionColor, shape = CircleShape)
                }

                day.date == today -> {
                    textColor(colorResource(R.color.black))
                    padding(padding)
                        .border(
                            width = 1.dp,
                            shape = CircleShape,
                            color = colorResource(R.color.black),
                        )
                        .background(color = Color.Transparent, CircleShape)
                }

                isScheduled -> {
                    textColor(colorResource(R.color.black))
                    padding(vertical = padding)
                        .padding(horizontal = padding)
                        .background(color = colorResource(R.color.light_green), CircleShape)
                }

                else -> {
                    textColor(Color.Black)
                    this
                }
            }
        }

        DayPosition.InDate -> {
            textColor(Color.Transparent)
            if (startDate != null && endDate != null &&
                ContinuousSelectionHelper.isInDateBetweenSelection(day.date, startDate, endDate)
            ) {
                padding(vertical = padding)
                    .background(color = continuousSelectionColor)
            } else {
                this
            }
        }

        DayPosition.OutDate -> {
            textColor(Color.Transparent)
            if (startDate != null && endDate != null &&
                ContinuousSelectionHelper.isOutDateBetweenSelection(day.date, startDate, endDate)
            ) {
                padding(vertical = padding)
                    .background(color = continuousSelectionColor)
            } else {
                this
            }
        }
    }
}
