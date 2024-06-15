package com.example.skills.ui.components

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.data.api.AuthRequest
import com.example.skills.data.viewmodel.MY_LOG
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.ui.components.tools.EmailState
import com.example.skills.ui.components.tools.EmailStateSaver
import com.example.skills.ui.components.tools.LoadingScreen
import com.example.skills.ui.components.tools.PasswordState
import com.example.skills.ui.components.tools.Validator.isBirthDateValid
import com.example.skills.ui.theme.backgroundMaterial
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    navController: NavHostController,
    navigateToCodeVerification: () -> Unit,
    viewModel: MainViewModel,
    isClient: Boolean = false
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
                        IconButton(onClick = {
                            //navController.navigate(ScreenRole.RoleLayout.route)
                            navController.popBackStack()
                        }) {
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

        Box(Modifier.fillMaxSize()) {
            ContentSingIn(innerPadding, navigateToCodeVerification, viewModel, isClient)

            val isLoading by viewModel.isLoading.collectAsState()
            if (isLoading) {
                LoadingScreen()
            }
        }
    }
}

@Composable
fun BackButtonHandler(onBackPressed: () -> Unit) {
    BackHandler {
        onBackPressed()
    }
}

@Composable
fun ContentSingIn(
    innerPadding: PaddingValues,
    navigateToCodeVerification: () -> Unit,
    viewModel: MainViewModel,
    isClient: Boolean
) {
    val email by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var secondName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf("") }
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
                    onValueChange = { firstName = it.capitalize() },
                    label = "Имя"
                )
                Spacer(modifier = Modifier.height(spaceBetweenOutlinedTextField))
                CustomOutlinedTextField(
                    value = secondName,
                    onValueChange = { secondName = it.capitalize() },
                    label = "Фамилия"
                )

                if (isClient) {
                    Spacer(modifier = Modifier.height(spaceBetweenOutlinedTextField))

                    OutlinedTextField(
                        value = birthday,
                        onValueChange = { birthday = it },
                        label = { Text(text = "Дата рождения (dd.MM.yyyy)") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedLabelColor = Color.Gray,
                            unfocusedBorderColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                }
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
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
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
                    imeAction = ImeAction.Next
                )
                Password(
                    label = "Повторите пароль",
                    passwordState = passwordStateRepeat,
                    modifier = Modifier.focusRequester(focusRequester),
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            CustomButton(
                navigateTo = {
                    viewModel.logout()

                    //  If the birthDate = null, then the master is registered, otherwise the Client.
                    // Date format is yyyy-MM-dd.
                    if (emailState.isValid && passwordState.isValid) {
                        //if ((isClient && isBirthDateValid(birthday)) || !isClient) {
                            val authRequest = AuthRequest(
                                email = emailState.text.trim(),
                                password = passwordState.text.trim(),
                                firstName = firstName,
                                lastName = secondName,
                                phoneNumber = phone,
                                birthDate = if (!isClient) null else {
                                    val sourceFormat =
                                        SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                                    val desiredFormat =
                                        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

                                    try {
                                        val date = sourceFormat.parse(birthday)
                                        desiredFormat.format(date!!)
                                    } catch (e: Exception) {
                                        val date = sourceFormat.parse("17.04.2004")
                                        desiredFormat.format(date!!)
                                    }

                                }
                            )
                            viewModel.registerUser(passwordState.text, authRequest) { successful ->
                                if (successful) {
                                    navigateToCodeVerification.invoke()
                                }
                           }
                        //   } else { Log.e(MY_LOG, "BirthDate is not valid") }
                    } else {
                        Log.e(MY_LOG, "emailState.isValid && passwordState.isValid not valid")
                    }
                },
                enabled = if (firstName.isNotEmpty() && phone.isNotEmpty() && passwordState.text.trim()
                        .isNotEmpty() && emailState.text.trim()
                        .isNotEmpty() && passwordStateRepeat.text.trim()
                        .isNotEmpty() && (passwordStateRepeat.text == passwordState.text)
                ) true else false,
                buttonText = "Зарегистрироваться"
            )
        }
    }
}

var spaceBetweenOutlinedTextField = 10.dp