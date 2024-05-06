package com.example.skills.master.components.c

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.navigation.NavHostController
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterClientServicesScreen(navController: NavHostController) {
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
        MasterClientServices(innerPadding, navController)
    }
}

@Composable
fun MasterClientServices(
    innerPadding: PaddingValues,
    navController: NavHostController,
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
                    BookingItem(
                        "Маникюр класический",
                        800,
                        LocalDateTime.now().minusDays(10L),
                        60,
                        "Анкудинова Полина",
                        20,
                        Status.ACTUAL
                    ),
                    BookingItem(
                        "Маникюр класический",
                        800,
                        LocalDateTime.now(),
                        60,
                        "Анкудинова Полина",
                        20,
                        Status.ACTUAL
                    ),
                    BookingItem(
                        "Маникюр класический",
                        800,
                        LocalDateTime.now().plusDays(4L),
                        60,
                        "Анкудинова Полина",
                        20,
                        Status.ACTUAL
                    ),
                    BookingItem(
                        "Маникюр класический",
                        800,
                        LocalDateTime.now(),
                        60,
                        "Анкудинова Полина",
                        20,
                        Status.ACTUAL
                    ),
                    BookingItem(
                        "Маникюр класический",
                        800,
                        LocalDateTime.now(),
                        60,
                        "Анкудинова Полина",
                        20,
                        Status.ACTUAL
                    ),
                    BookingItem(
                        "Маникюр европейский",
                        1000,
                        LocalDateTime.now(),
                        75,
                        "Гиязов Арсель",
                        20,
                        Status.ARCHIVE,
                        isDone = true
                    ),
                    BookingItem(
                        "Маникюр европейский",
                        1000,
                        LocalDateTime.now(),
                        75,
                        "Гиязов Арсель",
                        20,
                        Status.ARCHIVE
                    )
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)
        ) {
            val groupedItems = if (selectedTwoSegment == "Актуальные") {
                bookingItems.filter { it.status == Status.ACTUAL }.groupByDate()
            } else {
                bookingItems.filter { it.status == Status.ARCHIVE }.groupByDate()
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
                items.forEach { bookingItem ->
                    item { BookingItemCard(bookingItem) }
                }
            }
        }
    }
}

fun List<BookingItem>.groupByDate(): Map<LocalDate, List<BookingItem>> {
    return this.groupBy { it.timeStart.toLocalDate() }
}

data class BookingItem(
    val serviceName: String,
    val price: Int,
    val timeStart: LocalDateTime,
    val duration: Int,
    val clientName: String,
    val clientAge: Int,
    val status: Status,
    val isDone: Boolean? = if (status == Status.ACTUAL) null else false,
    val comment: String? = null,
    val clientId: Long? = null,
    val masterId: Long = 123,
    val serviceId: Long = 123
)

enum class Status { ACTUAL, ARCHIVE }