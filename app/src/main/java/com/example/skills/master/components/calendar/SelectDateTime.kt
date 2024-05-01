package com.example.skills.master.components.calendar

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.skills.role.components.CustomButton
import com.example.skills.role.components.CustomOutlinedTextField
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Composable
fun SelectDateTime() {
    val scrollState = rememberScrollState()

    var intervals by remember { mutableStateOf(listOf<IntervalData>()) }
    var inEditMode by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(bottom = 110.dp, top = 10.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        intervals.forEach { interval ->
            Interval(
                initialStartTime = interval.startTime,
                initialEndTime = interval.endTime,
                isEditable = inEditMode
            )
        }

        if (inEditMode) {
            TextButton(
                onClick = { intervals = intervals + IntervalData("", "") },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(text = "+ Добавить интервал")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        CustomButton(
            navigateTo = {
                inEditMode = !inEditMode
            },
            buttonText = if (inEditMode) "Сохранить" else "Изменить"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Interval(
    initialStartTime: String,
    initialEndTime: String,
    isEditable: Boolean,
) {
    var startTime by remember { mutableStateOf(initialStartTime) }
    var endTime by remember { mutableStateOf(initialEndTime) }

    val timePickerState = rememberTimePickerState()
    var showTimePickerStart by remember { mutableStateOf(false) }
    var showTimePickerEnd by remember { mutableStateOf(false) }

    if (isEditable && showTimePickerStart) {
        TimePickerDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                TextButton(
                    onClick = {
                        val hours = timePickerState.hour
                        val minutes = timePickerState.minute
                        val time = LocalTime.of(hours, minutes)

                        startTime = time.format(DateTimeFormatter.ofPattern("HH:mm"))
                        showTimePickerStart = false
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showTimePickerStart = false
                    }
                ) { Text("Отмена") }
            }
        )
        {
            TimePicker(state = timePickerState)
        }
    }

    if (isEditable && showTimePickerEnd) {
        TimePickerDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                TextButton(
                    onClick = {
                        val hours = timePickerState.hour
                        val minutes = timePickerState.minute
                        val time = LocalTime.of(hours, minutes)

                        endTime = time.format(DateTimeFormatter.ofPattern("HH:mm"))
                        showTimePickerEnd = false
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
        )
        {
            TimePicker(state = timePickerState)
        }
    }

    Row(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val focusManager = LocalFocusManager.current

        CustomOutlinedTextField(
            readOnly = !isEditable,
            value = startTime,
            onValueChange = { startTime = it },
            label = "Время начала",
            width = 0.49f,
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                focusManager.clearFocus()
                                if(isEditable) showTimePickerStart = true
                            }
                        }
                    }
                }
        )

        Spacer(modifier = Modifier.width(10.dp))

        CustomOutlinedTextField(
            readOnly = !isEditable,
            value = endTime,
            onValueChange = { endTime = it },
            label = "Время окончания",
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                focusManager.clearFocus()
                                if(isEditable) showTimePickerEnd = true
                            }
                        }
                    }
                }
        )

    }
}

data class IntervalData(var startTime: String, var endTime: String)