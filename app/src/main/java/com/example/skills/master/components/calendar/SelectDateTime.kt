package com.example.skills.master.components.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skills.role.components.CustomButton
import com.example.skills.role.components.CustomOutlinedTextField

@Composable
fun SelectDateTime() {
    Column (modifier = Modifier
        .fillMaxSize().padding(bottom = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier.verticalScroll(scrollState),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {

            Column {
                Interval()
                Interval()
            }

            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "+ Добавить интервал")
            }
        }

        CustomButton(
            navigateTo = { /*TODO*/ },
            buttonText = "Сохранить"
        )
    }
}

@Composable
fun Interval() {
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomOutlinedTextField(
            value = startTime,
            onValueChange = { startTime = it },
            label = "Время начала",
            width = 0.49f
        )
        Spacer(modifier = Modifier.width(10.dp))

        CustomOutlinedTextField(
            value = endTime,
            onValueChange = { endTime = it },
            label = "Время окончания"
        )
    }
}
