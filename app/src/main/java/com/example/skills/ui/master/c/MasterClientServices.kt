package com.example.skills.ui.master.c

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skills.data.entity.RecordItem
import com.example.skills.data.viewmodel.MY_LOG
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.ui.master.b.calendar.clickable
import com.example.skills.ui.master.d.CustomAlertDialog
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
fun MasterClientServices(
    innerPadding: PaddingValues,
    viewModel: MainViewModel
) {
    val recordItems = viewModel.recordsLiveData.observeAsState()
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    val twoSegments = remember { listOf("Актуальные", "История") }
    var selectedTwoSegment by remember { mutableStateOf(twoSegments.first()) }
    val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))

    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        CustomAlertDialog(
            onDismiss = {
                showDialog = false
            },
            onExit = {
                Log.e(MY_LOG, "selectedDate is $selectedDate")

                selectedDate?.let {
                    viewModel.deleteAllRecords(it.toString()) {
                        showDialog = false
                    }
                }
            },
            "Отменить все записи",
            "Все записи на эту дату будут отменены, мы уведомим об этом клиентов"
        )
    }

    Column(
        modifier = Modifier.padding(
            top = innerPadding.calculateTopPadding().plus(6.dp),
            start = 16.dp,
            end = 16.dp
        )
    ) {
        SegmentedControl(
            twoSegments,
            selectedTwoSegment,
            onSegmentSelected = { selectedTwoSegment = it },
            modifier = Modifier.height(50.dp)
        ) {
            SegmentText(it, selectedTwoSegment == it)
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)
        ) {
            val groupedItems = if (selectedTwoSegment == "Актуальные") {
                recordItems.value?.filter { it.status == "CREATED" || it.status == "IN_PROGRESS" }
                    ?.groupByDate()
            } else {
                recordItems.value?.filter { it.status == "CANCELLED" || it.status == "COMPLETED" }
                    ?.groupByDate()
            }
            groupedItems?.forEach { (date, items) ->
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = date.format(formatter),
                            modifier = Modifier.padding(start = 10.dp, top = 20.dp),
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            maxLines = 1
                        )
                        Text(
                            text = "Отменить все записи",
                            color = Color.Gray,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            maxLines = 1,
                            modifier = Modifier
                                .padding(end = 10.dp, top = 20.dp)
                                .clickable {
                                    selectedDate = date
                                    showDialog = true
                                }
                        )
                    }
                }
                items.forEach { bookingItem ->
                    item { RecordItemCard(bookingItem, mainViewModel = viewModel) }
                }
            } ?: run {
                item {
                    Text(
                        text = "Нет доступных записей",
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}


fun List<RecordItem>.groupByDate(): Map<LocalDate, List<RecordItem>> {
    return this.groupBy {
        val dateTime =
            LocalDateTime.parse("${it.date}T${it.timeFrom}", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        dateTime.toLocalDate()
    }
}

