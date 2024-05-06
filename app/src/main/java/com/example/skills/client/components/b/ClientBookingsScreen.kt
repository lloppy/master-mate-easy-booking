package com.example.skills.client.components.b

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.skills.data.models.RecordStatus
import com.example.skills.data.viewmodel.EditBookingViewModel
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.master.components.c.RecordItemCard
import com.example.skills.master.components.c.SegmentText
import com.example.skills.master.components.c.SegmentedControl
import com.example.skills.master.components.c.groupByDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientBookingsScreen(
    navController: NavHostController,
    editBookingViewModel: EditBookingViewModel
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
                        "Мои записи",
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
        MasterClientServices(innerPadding, navController, editBookingViewModel)
    }
}

@Composable
fun MasterClientServices(
    innerPadding: PaddingValues,
    navController: NavHostController,
    editBookingViewModel: EditBookingViewModel?
) {
    Column(
        modifier = Modifier.padding(
            top = innerPadding.calculateTopPadding().plus(6.dp),
            start = 16.dp,
            end = 16.dp
        )
    ) {
        val mainViewModel = MainViewModel()
        val records by remember { mutableStateOf(mainViewModel.recordsItemList) }

        val twoSegments = remember { listOf("Актуальные", "История") }
        var selectedTwoSegment by remember { mutableStateOf(twoSegments.first()) }
        SegmentedControl(
            twoSegments,
            selectedTwoSegment,
            onSegmentSelected = { selectedTwoSegment = it },
            modifier = Modifier.height(50.dp)
        ) {
            SegmentText(it, selectedTwoSegment == it)
        }
        Spacer(modifier = Modifier.height(16.dp))

        val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)
        ) {
            val groupedItems = if (selectedTwoSegment == "Актуальные") {
                records.filter { it.status == RecordStatus.ACTUAL }.groupByDate()
            } else {
                records.filter { it.status == RecordStatus.ARCHIVE }.groupByDate()
            }
            groupedItems.forEach { (date, items) ->
                item {
                    Text(
                        text = date.format(formatter),
                        modifier = Modifier.padding(start = 10.dp, top = 20.dp),
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        maxLines = 1
                    )
                }
                items.forEach { recordItem ->
                    item {
                        editBookingViewModel!!.data1 =
                            MutableLiveData(mainViewModel.getMaster(recordItem.masterId))
                        editBookingViewModel.data2 =
                            MutableLiveData(mainViewModel.getService(recordItem.serviceId))

                        RecordItemCard(recordItem, true, navController, editBookingViewModel)
                    }
                }
            }
        }
    }
}
