package com.example.skills.client.components.a

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.skills.data.viewmodel.BookingViewModel
import com.example.skills.role.components.CustomButton
import com.example.skills.role.components.CustomOutlinedTextField
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmClientBookingScreen(
    navController: NavHostController,
    bookingViewModel: BookingViewModel,
    navigateToDoneBooking: () -> Unit
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
                        "Подтвердите запись",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        ConfirmClientBookingContent(
            innerPadding,
            bookingViewModel,
            navigateToDoneBooking
        )
    }
}


@Composable
fun ConfirmClientBookingContent(
    innerPadding: PaddingValues,
    bookingViewModel: BookingViewModel,
    navigateToDoneBooking: () -> Unit
) {
    val master = bookingViewModel.data1.value!!
    val singleService = bookingViewModel.data2.value!!
    val date = bookingViewModel.data3.value!!
    val time = bookingViewModel.data4.value!!

    var comment by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding().plus(16.dp), bottom = 100.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column (modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Text(
                text = date.format(DateTimeFormatter.ofPattern("d MMMM", Locale("ru")))
                        + " в " + time.format(DateTimeFormatter.ofPattern("HH:mm")),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 8.dp)
            )

            ServiceCardClient(
                singleService, { }, master, bookingViewModel
            )
            Spacer(modifier = Modifier.height(12.dp))

            Column (modifier = Modifier.padding(start = 6.dp, end = 6.dp)) {
                CustomOutlinedTextField(
                    value = comment,
                    onValueChange = { comment = it },
                    label = "Комментарий"
                )
            }
        }
        CustomButton(
            navigateTo = {
                bookingViewModel.data5 = MutableLiveData(comment)
                navigateToDoneBooking.invoke()
            },
            buttonText = "Подтвердить"
        )
    }
}