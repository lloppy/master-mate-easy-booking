package com.example.skills.ui.master.c

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.data.entity.RecordItem
import com.example.skills.data.entity.RecordStatus
import com.example.skills.data.viewmodel.MyRepository.getRecordsItemList
import com.example.skills.ui.master.b.calendar.clickable
import com.example.skills.ui.master.d.CustomAlertDialog
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterClientServicesScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Записи клиентов",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            )
        }
    ) { innerPadding ->
        MasterClientServices(innerPadding)
    }
}

@Composable
fun MasterClientServices(
    innerPadding: PaddingValues,
) {
    val recordItems by remember { mutableStateOf(getRecordsItemList()) }
    var selectedDate: LocalDate? = null

    val twoSegments = remember { listOf("Актуальные", "История") }
    var selectedTwoSegment by remember { mutableStateOf(twoSegments.first()) }

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        CustomAlertDialog(
            onDismiss = {
                showDialog = false
            },
            onExit = {
                showDialog = false

                recordItems.clear()
                // recordItems.toMutableList().removeIf { it.timeFrom.toLocalDate() != selectedDate }
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
        ) { SegmentText(it, selectedTwoSegment == it) }
        Spacer(modifier = Modifier.height(16.dp))

        val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)
        ) {
            val groupedItems = if (selectedTwoSegment == "Актуальные") {
                recordItems.filter { it.recordStatus == RecordStatus.ACTUAL }.groupByDate()
            } else {
                recordItems.filter { it.recordStatus == RecordStatus.ARCHIVE }.groupByDate()
            }
            groupedItems.forEach { (date, items) ->
                item {
                    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
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
                            modifier = Modifier.padding(end = 10.dp, top = 20.dp)
                                .clickable {
                                    selectedDate = date
                                    showDialog = true
                                }
                        )
                    }
                }
                items.forEach { bookingItem ->
                    item { RecordItemCard(bookingItem) }
                }
            }
        }
    }
}

fun List<RecordItem>.groupByDate(): Map<LocalDate, List<RecordItem>> {
    return this.groupBy { it.timeFrom.toLocalDate() }
}

