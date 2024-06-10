package com.example.skills.ui.client.a.edit_booking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
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
import com.example.skills.data.viewmodel.route.EditBookingViewModel
import com.example.skills.ui.components.CustomButton
import com.example.skills.ui.components.CustomOutlinedTextField
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditConfirmClientBookingScreen(
    navController: NavHostController,
    editBookingViewModel: EditBookingViewModel,
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
                        "Подтвердите изменения",
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
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        ConfirmClientBookingContent(
            innerPadding,
            editBookingViewModel,
            navigateToDoneBooking
        )
    }
}


@Composable
fun ConfirmClientBookingContent(
    innerPadding: PaddingValues,
    editBookingViewModel: EditBookingViewModel,
    navigateToDoneBooking: () -> Unit
) {
    val master = editBookingViewModel.data1.value!!
    val singleService = editBookingViewModel.data2.value!!
    val date = editBookingViewModel.data3.value!!
    val time = editBookingViewModel.data4.value!!

    var comment by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = innerPadding
                    .calculateTopPadding()
                    .plus(16.dp), bottom = 100.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Text(
                text = date.format(DateTimeFormatter.ofPattern("d MMMM", Locale("ru")))
                        + " в " + time.from.format(DateTimeFormatter.ofPattern("HH:mm"))
                    .substring(0, time.from.length - 3),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 8.dp)
            )

            EditServiceCardClient(
                singleService, { }, master, editBookingViewModel
            )
            Spacer(modifier = Modifier.height(12.dp))

            Column(modifier = Modifier.padding(start = 6.dp, end = 6.dp)) {
                CustomOutlinedTextField(
                    value = comment,
                    onValueChange = { comment = it },
                    label = "Комментарий"
                )
            }
        }
        CustomButton(
            navigateTo = {
                editBookingViewModel.data5 = MutableLiveData(comment)
                navigateToDoneBooking.invoke()
            },
            buttonText = "Подтвердить"
        )
    }
}