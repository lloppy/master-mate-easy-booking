package com.example.skills.master.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.role.ScreenRole
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeVerificationMasterScreen(
    navController: NavHostController,
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
                        "Подтвердите Email",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                navigationIcon = {
                    Row {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                tint = Color.Black,
                                contentDescription = "Localized description"
                            )
                        }
                    }

                }
            )
        },
    ) { innerPadding ->
        CodeVerificationComponents(navController, innerPadding)
    }
}

@Composable
private fun CodeVerificationComponents(navController: NavHostController, innerPadding: PaddingValues) {
    var code by remember { mutableStateOf("") }
    var timeLeft by remember { mutableStateOf(55) }

    LaunchedEffect(key1 = true) {
        while (timeLeft > 0) {
            delay(1000L)
            timeLeft -= 1
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = innerPadding
                    .calculateTopPadding()
                    .plus(16.dp),
                start = 16.dp,
                end = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text("На ваш Email был отправлен код, введите его ниже.", fontSize = 14.sp)
        Spacer(modifier = Modifier.height(32.dp))

        OtpTextField(
            otpText = code,
            onOtpTextChange = { value, otpInputFilled ->
                code = value
            }
        )

        if (timeLeft > 0) {
            Text("Отправить код повторно через $timeLeft сек", fontSize = 14.sp)
        } else {
            TextButton(onClick = { timeLeft = 55 }) {
                Text("Отправить код повторно", fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(
            ScreenRole.DoneMasterRegistration.route,
             navController,
            "Подтвердить",
            height = 0.14f
        )
    }
}
