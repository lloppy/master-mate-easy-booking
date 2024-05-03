package com.example.skills.master.components.c

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.example.skills.master.components.d.Category
import java.time.LocalDate
import java.time.LocalDateTime

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
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier.padding(
            top = innerPadding.calculateTopPadding().plus(6.dp),
            start = 16.dp,
            end = 16.dp
        )
    ) {
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

        val bookingItems by remember {
            mutableStateOf(
                listOf<BookingItem>(
                    BookingItem("Маникюр класический", 800, LocalDateTime.now(), 60, "Анкудинова Полина", 20, Status.ACTUAL ),
                    BookingItem("Маникюр европейский", 1000, LocalDateTime.now(), 75, "Гиязов Арсель", 20, Status.ARCHIVE, isDone = true ),

                )
            )
        }

        LazyRow(Modifier.fillMaxWidth()) {
            if (selectedTwoSegment == "Актуальные") {
                items(bookingItems.filter { it.status == Status.ACTUAL }) { bookingItem ->
                    Text(text = bookingItem.serviceName)
                    Text(text = bookingItem.isDone.toString())
                }
            } else {
                items(bookingItems.filter { it.status == Status.ARCHIVE }) { bookingItem ->
                    Text(text = bookingItem.serviceName)
                    Text(text = bookingItem.isDone.toString())

                }
            }
        }
    }


}

data class BookingItem(
    val serviceName: String,
    val price: Int,
    val timeStart: LocalDateTime,
    val duration: Int,
    val clientName: String,
    val clientAge: Int,
    val status: Status,
    val isDone: Boolean? = if (status == Status.ACTUAL) null else false
)

enum class Status { ACTUAL, ARCHIVE }