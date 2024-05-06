package com.example.skills.client.components.a.new_booking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
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
import com.example.skills.data.viewmodel.BookingViewModel
import com.example.skills.master.components.b.calendar.ContinuousSelectionHelper.getSelection
import com.example.skills.master.components.b.calendar.DateSelection
import com.example.skills.master.components.b.calendar.backgroundHighlight
import com.example.skills.master.components.b.calendar.clickable
import com.example.skills.role.components.CustomButton
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import java.time.LocalDate
import java.time.YearMonth

private val primaryColor = Color.Black.copy(alpha = 0.9f)
private val selectionColor = primaryColor
private val continuousSelectionColor = Color.LightGray.copy(alpha = 0.3f)

@Composable
fun ClientCalendarView(
    bookingViewModel: BookingViewModel,
    navigateToSelectTime: () -> Unit,
    close: () -> Unit = {},
    dateSelected: (startDate: LocalDate, endDate: LocalDate) -> Unit = { _, _ -> },
) {
    val rusDaysOfWeek = listOf("ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС")
    val daysOfWeek = remember { rusDaysOfWeek }

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
                Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp),
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
                                Icon(Icons.Filled.ArrowBack, contentDescription = "Назад")
                            }
                            IconButton(onClick = {
                                currentMonth = currentMonth.plusMonths(1)
                            }) {
                                Icon(Icons.Filled.ArrowForward, contentDescription = "Вперед")
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
                        monthHeader = { month -> MonthHeader(month) },
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

@Composable
private fun Day(
    day: CalendarDay,
    today: LocalDate,
    selection: DateSelection,
    onClick: (CalendarDay) -> Unit,
) {
    var textColor = Color.Transparent

    Box(
        modifier = Modifier
            .aspectRatio(1f) // This is important for square-sizing!
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

@Composable
private fun MonthHeader(calendarMonth: CalendarMonth) {
    Box(modifier = Modifier.fillMaxWidth()) {
        val rusDaysOfWeek = listOf("ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС")
        val daysOfWeek = remember { rusDaysOfWeek }

        Row(modifier = Modifier.fillMaxWidth()) {
            for (dayOfWeek in daysOfWeek) {
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    color = Color.LightGray,
                    text = dayOfWeek,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

val monthNames = listOf(
    "январь",
    "февраль",
    "март",
    "апрель",
    "май",
    "июнь",
    "июль",
    "август",
    "сентябрь",
    "октябрь",
    "ноябрь",
    "декабрь"
)