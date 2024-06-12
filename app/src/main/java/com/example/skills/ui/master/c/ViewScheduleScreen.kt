package com.example.skills.ui.master.c

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.data.entity.TimeSlot
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.ui.components.tools.LoadingScreen
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewScheduleScreen(
    navController: NavHostController,
    viewModel: MainViewModel
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
                        "Ваше расписание",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                navigationIcon = {
                    Row {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                tint = Color.Black,
                                contentDescription = "Localized description"
                            )
                        }
                    }

                }
            )
        }
    ) { innerPadding ->
        val isLoading by viewModel.isLoading.collectAsState()
        if (isLoading) {
            LoadingScreen()
        } else {
            ViewScheduleContent(innerPadding, viewModel)
        }
    }
}


@Composable
fun ViewScheduleContent(
    innerPadding: PaddingValues,
    viewModel: MainViewModel
) {
    val schedule = viewModel.schedulesLiveData.observeAsState()
    val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))

    Column(
        modifier = Modifier.padding(
            top = innerPadding.calculateTopPadding().plus(6.dp),
            start = 16.dp,
            end = 16.dp
        )
    ) {
        if (schedule.value.isNullOrEmpty()) {
            Text(
                text = "Расписание еще не создано",
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 100.dp)
            ) {
                schedule.value?.let { schedules ->
                    val sortedSchedules = schedules.sortedBy { LocalDate.parse(it.date) }

                    for (schedule in sortedSchedules) {
                        val sortedTimeSlots =
                            schedule.timeSlots.sortedBy { LocalTime.parse(it.from) }

                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = LocalDate.parse(schedule.date).format(formatter),
                                    modifier = Modifier.padding(start = 10.dp, top = 20.dp),
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp,
                                    maxLines = 1
                                )
                            }
                        }

                        sortedTimeSlots.forEach { timeSlot ->
                            item { TimeSlotItemCard(timeSlot = timeSlot) }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TimeSlotItemCard(timeSlot: TimeSlot) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color.Black,
        modifier = Modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Начало: ${timeSlot.from.substring(0, timeSlot.to.length - 3)}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Конец: ${timeSlot.to.substring(0, timeSlot.to.length - 3)}",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
    }
}