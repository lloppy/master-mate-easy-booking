package com.example.skills.ui.client.a

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.rounded.DeleteOutline
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.data.api.MasterForClient
import com.example.skills.data.viewmodel.MY_LOG
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.data.viewmodel.route.BookingViewModel
import com.example.skills.ui.master.d.CustomAlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewMasterScreen(
    bookingViewModel: BookingViewModel,
    navController: NavHostController,
    navigateToServices: () -> Unit,
    mainViewModel: MainViewModel
) {
    var showDialog by remember { mutableStateOf(false) }
    val master = bookingViewModel.data1.value!!

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        master.fullName.toString(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                actions = {
                    IconButton(onClick = {
                        //TODO удаляем из бдшки и обновляем :) 😊👌
                        showDialog = true
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.DeleteOutline,
                            contentDescription = "Localized description"
                        )
                    }
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
        MasterHomeScreen(
            innerPadding,
            master,
            navigateToServices,
            bookingViewModel,
            mainViewModel
        )

        if (showDialog) {
            CustomAlertDialog(
                onDismiss = {
                    showDialog = false
                },
                onExit = {
                    showDialog = false
                },
                title = "Удалить мастера",
                description = "Мастер будет удален из списка мастеров"
            )
        }
    }
}


@Composable
fun MasterHomeScreen(
    innerPadding: PaddingValues,
    user: MasterForClient,
    navigateToServices: () -> Unit,
    bookingViewModel: BookingViewModel,
    mainViewModel: MainViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        bookingViewModel.data1.value = user

        val receivedCategories = bookingViewModel.data1.value!!.categories
        if (receivedCategories != null) {
            try {
                mainViewModel.getServicesByCategoryId(receivedCategories) { success ->
                    Log.e(MY_LOG, "getServicesByCategoryId is successful")
                }
                mainViewModel.getSchedulesById(user.masterId!!) { success ->
                    Log.e(MY_LOG, "getSchedulesById is successful")
                }
            } catch (e: Exception) { }
        }
        Log.e(MY_LOG, "bookingViewModel user categ is ${user.categories?.firstOrNull()}")

        ViewMasterHead(user, navigateToServices)
        //TODO if (user.master?.images != null) MasterGallery(user.master!!.images!!)
    }
}