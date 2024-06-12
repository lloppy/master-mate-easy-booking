package com.example.skills.ui.master.b.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.skills.data.api.ScheduleCreateRequest
import com.example.skills.data.entity.TimeSlot
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.ui.components.CustomButton
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDateTime(viewModel: MainViewModel, selection: DateSelection) {
    val scrollState = rememberScrollState()
    val inEditMode by remember { mutableStateOf(true) }

    var showTimePickerStart by remember { mutableStateOf(false) }
    var showTimePickerEnd by remember { mutableStateOf(false) }

    var newIntervalStartTime by remember { mutableStateOf("") }
    var newIntervalEndTime by remember { mutableStateOf("") }
    val timePickerState = rememberTimePickerState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(bottom = 110.dp, top = 10.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        if (inEditMode) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomButton(
                    {
                        val dates = getDatesBetween(selection.startDate, selection.endDate)
                        viewModel.deleteSchedule(dates) {}
                        try {
                            viewModel.getScheduleByToken {}
                        } catch (e: Exception) {
                        }
                    },
                    "Удалить",
                    color = Color.Transparent,
                    width = 0.5f
                )
                CustomButton(
                    {
                        showTimePickerStart = true
                    },
                    "Добавить"
                )
            }
        }

        if (showTimePickerStart) {
            TimePickerDialog(
                title = "Время начала",
                onDismissRequest = { showTimePickerStart = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val hours = timePickerState.hour
                            val minutes = timePickerState.minute
                            val time = LocalTime.of(hours, minutes)

                            newIntervalStartTime = time.format(DateTimeFormatter.ofPattern("HH:mm"))
                            showTimePickerStart = false
                            showTimePickerEnd = true
                        }
                    ) { Text("OK") }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showTimePickerEnd = false
                        }
                    ) { Text("Отмена") }
                }
            ) { TimePicker(state = timePickerState) }
        }

        if (showTimePickerEnd) {
            TimePickerDialog(
                title = "Время окончания",
                onDismissRequest = { showTimePickerEnd = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val hours = timePickerState.hour
                            val minutes = timePickerState.minute
                            val time = LocalTime.of(hours, minutes)

                            newIntervalEndTime = time.format(DateTimeFormatter.ofPattern("HH:mm"))
                            showTimePickerEnd = false

                            val dates = getDatesBetween(selection.startDate, selection.endDate)
                            val scheduleRequest = ScheduleCreateRequest(
                                dates = dates,
                                timeSlots = List(1) {
                                    TimeSlot(
                                        from = newIntervalStartTime,
                                        to = newIntervalEndTime
                                    )
                                }
                            )
                            viewModel.createSchedule(scheduleRequest)
                        }
                    ) { Text("OK") }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showTimePickerEnd = false
                        }
                    ) { Text("Отмена") }
                }
            ) { TimePicker(state = timePickerState) }
        }
    }
}

private fun getDatesBetween(from: LocalDate?, to: LocalDate?): List<String> {
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())

    if (from == null) {
        return listOf(to?.format(format).orEmpty())
    }
    if (to == null) {
        return listOf(from.format(format))
    }
    val dates = mutableListOf<String>()

    var currentDate = from
    while (!currentDate!!.isAfter(to)) {
        dates.add(currentDate.format(format))
        currentDate = currentDate.plusDays(1)
    }
    return dates
}

data class IntervalData(var from: String, var to: String)