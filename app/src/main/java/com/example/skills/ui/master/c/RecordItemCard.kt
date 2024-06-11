package com.example.skills.ui.master.c

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.skills.R
import com.example.skills.data.entity.RecordItem
import com.example.skills.data.entity.RecordStatus
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.data.viewmodel.route.EditBookingViewModel
import com.example.skills.data.viewmodel.MyRepository.getMaster
import com.example.skills.navigation.ScreenRole
import com.example.skills.ui.master.d.CustomAlertDialog
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


@SuppressLint("DefaultLocale")
@Composable
fun RecordItemCard(
    recordItem: RecordItem,
    isClient: Boolean = false,
    navController: NavHostController? = null, // возможны нул значения для мастера, для клиента всегда не нул
    editBookingViewModel: EditBookingViewModel? = null,
    mainViewModel: MainViewModel
) {
    val masters by mainViewModel.mastersForClient.collectAsState()

    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var showCalendarDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        CustomAlertDialog(
            onDismiss = {
                showDialog = false
            },
            onExit = {
                mainViewModel.deleteRecord(recordItem.id) {
                    showDialog = false
                }
            },
            "Отменить запись",
            "Запись будет отменена, мы уведомим об этом " + if (isClient) "мастера" else "клиента"
        )
    }

    if (showCalendarDialog) {
        CustomAlertDialog(
            onDismiss = {
                showCalendarDialog = false
            },
            onExit = {
                showCalendarDialog = false
                openGoogleCalendar(context, recordItem)
            },
            "Google Календарь",
            "Добавить событие в Google Календарь"
        )
    }

    Column(
        modifier = Modifier
            .padding(bottom = 10.dp, top = 10.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .height(
                164.dp
                    .plus(if (recordItem.status == "CANCELLED" || recordItem.status == "COMPLETED") 24.dp else 0.dp)
                    .minus(if (isClient && recordItem.comment == null) 24.dp else 0.dp)
            )
            .border(1.dp, Color.LightGray, RoundedCornerShape(20.dp))
            .background(Color.White)
    ) {
        Column(modifier = Modifier.padding(start = 20.dp, end = 15.dp)) {
            Spacer(modifier = Modifier.height(paddingBetweenText.plus(paddingBetweenText)))

            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            val startTime = LocalTime.parse(recordItem.timeFrom, timeFormatter)
            val totalDurationMinutes = recordItem.service.duration.hours * 60 + recordItem.service.duration.minutes

            val timeEnd = startTime.plusMinutes(totalDurationMinutes.toLong())

            Box(modifier = Modifier.fillMaxWidth()) {
                Column {
                    if (recordItem.status == "CANCELLED" || recordItem.status == "COMPLETED") {
                        if (recordItem.status == "COMPLETED") {
                            BadgeCard("Выполнена", Color(41, 174, 41))
                        } else {
                            BadgeCard("Отменена", Color(236, 19, 19))
                        }
                    }
                    Text(
                        text = "${String.format("%02d", startTime.hour)}:${
                            String.format("%02d", startTime.minute)
                        } - ${String.format("%02d", timeEnd.hour)}:${
                            String.format("%02d", timeEnd.minute)
                        }",
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .padding(top = 10.dp)
                    )
                    Spacer(modifier = Modifier.height(paddingBetweenText))
                    Text(
                        text = recordItem.service.name.capitalize(),
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        maxLines = 1
                    )
                }

                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .align(Alignment.TopEnd)
                ) {
                    if (recordItem.status == "CREATED" || recordItem.status == "IN_PROGRESS") {
                        IconButton(onClick = { showCalendarDialog = true }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_calendar),
                                contentDescription = "add to calendar",
                                tint = Color.Unspecified
                            )
                        }
                    }
                    IconButton(onClick = {
                        if (isClient) {
                            try {
                                editBookingViewModel!!.data1 =
                                    MutableLiveData(
                                        masters.first{it.masterId == recordItem.masterId}


                                    )
                                editBookingViewModel.data2 =
                                    MutableLiveData(mainViewModel.getService(recordItem.service.id))

                                navController!!.navigate(ScreenRole.Client.EditDate.route)
                            } catch (_: NullPointerException) {
                            }
                        }
                    }) {
                        val iconResId = when {
                            isClient && (recordItem.status == "CREATED" || recordItem.status == "IN_PROGRESS") -> R.drawable.edit
                            !isClient -> R.drawable.phone_circle
                            else -> null
                        }

                        if (iconResId != null) {
                            val painter = painterResource(id = iconResId)

                            Icon(
                                painter = painter,
                                contentDescription = "edit/phone",
                                tint = Color.Unspecified
                            )
                        }
                    }
                    if (recordItem.status == "CREATED" || recordItem.status == "IN_PROGRESS") {
                        IconButton(onClick = { showDialog = true }) {
                            Icon(
                                painter = painterResource(id = R.drawable.close_circle),
                                contentDescription = "close",
                                tint = Color.Unspecified
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(paddingBetweenText))
            Text(
                text = recordItem.service.price.toString() + " руб",
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                maxLines = 1
            )

            if (!isClient) {
                Spacer(modifier = Modifier.height(paddingBetweenText))
                val text = if (recordItem.comment.isNullOrEmpty() || recordItem.comment.isNullOrBlank()) ""  else "\nКоментарий: ${recordItem.comment}"
                Text(
                    text = "Клиент: ${recordItem.client.fullName} ${recordItem.client.age} лет  " + text,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    maxLines = 3,
                    lineHeight = lineHeight
                )
            }
        }
    }
}


@Composable
fun BadgeCard(text: String, color: Color) {
    Box(
        modifier = Modifier
            .height(28.dp)
            .offset(x = -10.dp)
            .background(color.copy(0.1f), shape = RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = color,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 14.dp, end = 14.dp, top = 4.dp, bottom = 4.dp)
        )
    }
}


val paddingBetweenText = 8.dp
var lineHeight = 18.sp
var spacerValue = 14.dp
var instructionTextSize = 14.sp


fun openGoogleCalendar(context: Context, recordItem: RecordItem) {
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    val time = LocalTime.parse(recordItem.timeFrom, timeFormatter)
    val date = LocalDate.parse(recordItem.date)
    val dateTime = LocalDateTime.of(date, time)

    val zonedDateTime: ZonedDateTime = dateTime.atZone(ZoneId.systemDefault())

    val durationInMinutes = recordItem.service.duration.hours * 60 + recordItem.service.duration.minutes
    val startMillis: Long = zonedDateTime.toInstant().toEpochMilli()
    val endMillis: Long = startMillis + durationInMinutes * 60 * 1000

    val intent = Intent(Intent.ACTION_INSERT)
        .setData(CalendarContract.Events.CONTENT_URI)
        .putExtra(
            CalendarContract.EXTRA_EVENT_BEGIN_TIME,
            startMillis
        )
        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
        .putExtra(CalendarContract.Events.TITLE, recordItem.service.name)
        .putExtra(
            CalendarContract.Events.DESCRIPTION,
            recordItem.comment
        )
//        .putExtra(
//            CalendarContract.Events.EVENT_LOCATION,
//            recordItem.masterId
//        )
        .putExtra(
            CalendarContract.Events.AVAILABILITY,
            CalendarContract.Events.AVAILABILITY_BUSY
        )
        .putExtra(CalendarContract.Events.HAS_ALARM, 1)
    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(
            context,
            "Пожалуйста, установите Google Календарь",
            Toast.LENGTH_SHORT
        ).show()
    }
}
