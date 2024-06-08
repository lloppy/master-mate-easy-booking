package com.example.skills.client.components.a.new_booking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.data.viewmodel.route.BookingViewModel
import com.example.skills.ui.client.a.new_booking.ClientCalendarView
import com.example.skills.ui.components.tools.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDateScreen(
    bookingViewModel: BookingViewModel,
    navController: NavHostController,
    navigateToSelectTime: () -> Unit,
    mainViewModel: MainViewModel
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
                        "Выберите дату",
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
        val isLoading by mainViewModel.isLoading.collectAsState()
        if (isLoading) {
            LoadingScreen()
        } else {
            CustomCalendarView(
                innerPadding,
                navController,
                bookingViewModel,
                navigateToSelectTime,
                mainViewModel
            )
        }
    }
}

@Composable
fun CustomCalendarView(
    innerPadding: PaddingValues,
    navController: NavHostController,
    bookingViewModel: BookingViewModel,
    navigateToSelectTime: () -> Unit,
    mainViewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding())
    ) {
        ClientCalendarView(
            bookingViewModel = bookingViewModel,
            navigateToSelectTime = navigateToSelectTime,
            mainViewModel = mainViewModel
        )
    }
}