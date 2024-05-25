package com.example.skills.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.data.api.AuthRequest
import com.example.skills.data.viewmodel.MY_LOG
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.ui.components.tools.EmailState
import com.example.skills.ui.components.tools.EmailStateSaver
import com.example.skills.ui.components.tools.PasswordState
import com.example.skills.ui.theme.backgroundMaterial
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    navController: NavHostController,
    navigateToCodeVerification: () -> Unit,
    viewModel: MainViewModel
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
                        "Регистрация",
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
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                tint = Color.Black,
                                contentDescription = "Localized description"
                            )
                        }
                    }

                }
            )
        },
    ) { innerPadding ->
        ContentSingIn(innerPadding, navigateToCodeVerification, viewModel)
    }
}

@Composable
fun ContentSingIn(
    innerPadding: PaddingValues,
    navigateToCodeVerification: () -> Unit,
    viewModel: MainViewModel
) {
    var email by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var secondName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
        mutableStateOf(EmailState(email))
    }
    val passwordState = remember { PasswordState() }
    val passwordStateRepeat = remember { PasswordState() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundMaterial)
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 8.dp,
                    end = 8.dp
                )
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                CustomOutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = "Имя"
                )
                Spacer(modifier = Modifier.height(spaceBetweenOutlinedTextField))
                CustomOutlinedTextField(
                    value = secondName,
                    onValueChange = { secondName = it },
                    label = "Фамилия"
                )

                Spacer(modifier = Modifier.height(spaceBetweenOutlinedTextField))
                Email(emailState, onImeAction = { focusRequester.requestFocus() })

                Spacer(modifier = Modifier.height(spaceBetweenOutlinedTextField))
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text(text = "Номер телефона") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.bodyMedium,
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedLabelColor = Color.Gray,
                        unfocusedBorderColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(16.dp)
                )

                Spacer(modifier = Modifier.height(spaceBetweenOutlinedTextField))
                Password(
                    label = "Пароль",
                    passwordState = passwordState,
                    modifier = Modifier.focusRequester(focusRequester),
//                onImeAction = {
//                    onSubmit()
//                }
                )
                Password(
                    label = "Повторите пароль",
                    passwordState = passwordStateRepeat,
                    modifier = Modifier.focusRequester(focusRequester),
//                onImeAction = {
//                    onSubmit()
//                }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            CustomButton(
                navigateTo = {
                    Log.e(MY_LOG, "click!")

                    if (emailState.isValid && passwordState.isValid) {
                        val authRequest = AuthRequest(
                            email = emailState.text.trim(),
                            password = passwordState.text.trim(),
                            firstName = firstName,
                            lastName = secondName,
                            phoneNumber = phone,
                            birthDate = LocalDate.now().toString()
                        )
                        viewModel.registerUser(authRequest) { successful ->
                            if (successful) {
                                navigateToCodeVerification.invoke()
                            }
                        }
                    } else {
                        Log.e(MY_LOG, "emailState.isValid && passwordState.isValid not valid")
                    }
                },
                buttonText = "Зарегистрироваться"
            )
        }
    }
}

var spaceBetweenOutlinedTextField = 10.dp