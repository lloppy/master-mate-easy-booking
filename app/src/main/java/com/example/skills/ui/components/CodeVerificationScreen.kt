package com.example.skills.ui.components

import android.util.Log
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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.example.skills.data.api.ActivationRequest
import com.example.skills.data.viewmodel.MY_LOG
import com.example.skills.data.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeVerificationScreen(
    navController: NavHostController,
    navigateToDoneRegistration: (() -> Unit)? = null,
    navigateToCreateNewPassword: (() -> Unit)? = null,
    viewModel: MainViewModel
) {
    var activationCode by remember { mutableStateOf("") }

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
        CodeVerificationComponents(
            innerPadding,
            navigateToDoneRegistration ?: navigateToCreateNewPassword!!,
            viewModel
        )
    }
}

@Composable
private fun CodeVerificationComponents(
    innerPadding: PaddingValues,
    navigateTo: () -> Unit,
    viewModel: MainViewModel
) {
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
                start = 8.dp,
                end = 8.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            "На ваш Email был отправлен код, введите его ниже.",
            fontSize = 14.sp,
            modifier = Modifier.padding(
                start = 8.dp,
                end = 8.dp
            )
        )
        Spacer(modifier = Modifier.height(32.dp))

        OtpTextField(
            modifier = Modifier.padding(
                start = 8.dp,
                end = 8.dp
            ),
            otpText = code,
            onOtpTextChange = { value, otpInputFilled ->
                code = value
            }
        )

        if (timeLeft > 0) {
            Text("Отправить код повторно через $timeLeft сек", fontSize = 14.sp)
        } else {
            TextButton(onClick = {
                timeLeft = 55

                viewModel.activateAccount(ActivationRequest(code)) { isSuccess ->
                    if (isSuccess) {
                        navigateTo.invoke()
                    }
                }
            }) {
                Text("Отправить код повторно", fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(
            {
                Log.d(MY_LOG, "code is $code")

                viewModel.activateAccount(ActivationRequest(code)) { successful ->
                    if (successful) {
                        navigateTo.invoke()
                    }
                }
            },
            "Подтвердить"
        )
    }
}


@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 4,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    LaunchedEffect(Unit) {
        if (otpText.length > otpCount) {
            throw IllegalArgumentException("Otp text value must not have more than otpCount: $otpCount characters")
        }
    }

    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f),
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        onValueChange = {
            if (it.text.length <= otpCount) {
                onOtpTextChange.invoke(it.text, it.text.length == otpCount)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Top) {
                repeat(otpCount) { index ->
                    CharView(
                        index = index,
                        text = otpText
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    )
}

@Composable
fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> "0"
        index > text.length -> ""
        else -> text[index].toString()
    }
    Text(
        modifier = Modifier
            .width(80.dp)
            .height(80.dp)
            .border(
                1.dp, when {
                    isFocused -> Color.Black
                    else -> Color.LightGray
                }, RoundedCornerShape(8.dp)
            )
            .padding(22.dp),
        text = char,
        color = if (isFocused) {
            Color.LightGray
        } else {
            Color.Black
        },
        textAlign = TextAlign.Center,
        fontSize = 24.sp
    )
}