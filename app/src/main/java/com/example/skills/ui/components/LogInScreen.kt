package com.example.skills.ui.components

import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.data.api.LogInRequest
import com.example.skills.data.viewmodel.MY_LOG
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.ui.components.tools.EmailState
import com.example.skills.ui.components.tools.EmailStateSaver
import com.example.skills.ui.components.tools.LoadingScreen
import com.example.skills.ui.components.tools.PasswordState
import com.example.skills.ui.components.tools.PasswordStateSaver
import com.example.skills.ui.components.tools.TextFieldState
import com.example.skills.ui.theme.backgroundMaterial

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    navController: NavHostController,
    routeLogIn: String,
    navigateToRegistration: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    navigateToMain: () -> Unit,
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
                        "Вход", // + routeLogIn.take(6), // master or client route
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
        }
    ) { innerPadding ->
        Box(Modifier.fillMaxSize()) {
            ContentLogIn(
                innerPadding,
                routeLogIn = routeLogIn,
                navigateToRegistration,
                navigateToForgotPassword,
                navigateToMain,
                mainViewModel
            )
            val isLoading by mainViewModel.isLoading.collectAsState()
            if (isLoading) {
                LoadingScreen()
            }
        }
    }
}

@Composable
fun ContentLogIn(
    innerPadding: PaddingValues,
    routeLogIn: String,
    navigateToRegistration: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    navigateToMain: () -> Unit,
    viewModel: MainViewModel
) {
    val email by remember {
        mutableStateOf(
            if (viewModel.userIsAuthenticated.value) {
                viewModel.currentUser?.email
            } else ""
        )
    }
    val focusRequester = remember { FocusRequester() }
    val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
        mutableStateOf(
            EmailState(
                email
            )
        )
    }

    val password by remember {
        mutableStateOf(
            if (viewModel.userIsAuthenticated.value) {
                viewModel.currentUser?.password
            } else ""
        )
    }
    val passwordState by rememberSaveable(stateSaver = PasswordStateSaver) {
        mutableStateOf(
            PasswordState((if (password == null) "" else password).toString())
        )
    }

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
            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                Email(emailState, onImeAction = { focusRequester.requestFocus() })
                Spacer(modifier = Modifier.height(16.dp))
                Password(
                    label = "Пароль",
                    passwordState = passwordState,
                    modifier = Modifier.focusRequester(focusRequester),
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top
            ) {
                TextButton(onClick = navigateToForgotPassword) {
                    Text("Забыли пароль?", fontSize = 14.sp)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            CustomButton(
                navigateTo = {
                    Log.v(MY_LOG, "click!")

                    if (emailState.isValid && passwordState.isValid) {
                        val authRequest = LogInRequest(
                            email = emailState.text.trim(),
                            password = passwordState.text.trim()
                        )
                        viewModel.authenticate(routeLogIn.take(6), authRequest) { successful ->
                            if (successful) {
                                navigateToMain.invoke()
                            }
                        }
                    } else {
                        Log.e(MY_LOG, "emailState.isValid && passwordState.isValid not valid")
                    }
                },
                enabled = if (passwordState.text.trim().isNotEmpty() && emailState.text.trim()
                        .isNotEmpty()
                ) true else false,
                buttonText = "Войти"
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "У вас нет учетной записи?",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                TextButton(onClick = navigateToRegistration) {
                    Text(
                        "Зарегистрироваться",
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}


@Composable
fun Email(
    emailState: TextFieldState = remember { EmailState() },
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {},
    readOnly: Boolean = false
) {
    OutlinedTextField(
        value = emailState.text,
        onValueChange = {
            emailState.text = it
        },
        label = { Text(text = "Email") },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                emailState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    emailState.enableShowErrors()
                }
            },
        textStyle = MaterialTheme.typography.bodyMedium,
        isError = emailState.showErrors(),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = KeyboardType.Email
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
            }
        ),
        singleLine = true,
        colors = if (readOnly) {
            OutlinedTextFieldDefaults.colors(
                unfocusedLabelColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray,
                unfocusedTextColor = Color.LightGray,
                disabledTextColor = Color.LightGray
            )
        } else {
            OutlinedTextFieldDefaults.colors(
                unfocusedLabelColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedLabelColor = Color.Black
            )
        },
        shape = RoundedCornerShape(16.dp),
        readOnly = readOnly
    )

    //TODO сделать валидации ошибок, и для кастомного outliner
    emailState.getError()?.let { error -> TextFieldError(textError = error) }
}

@Composable
fun TextFieldError(textError: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = textError,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.error
        )
    }
}


@Composable
fun Password(
    label: String,
    passwordState: TextFieldState,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: @Composable () -> Unit = {}
) {
    val showPassword = rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        value = passwordState.text,
        onValueChange = {
            passwordState.text = it
            passwordState.enableShowErrors()
        },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                passwordState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    passwordState.enableShowErrors()
                }
            },
        label = { Text(text = label) },
        trailingIcon = {
            if (showPassword.value) {
                IconButton(onClick = { showPassword.value = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = "hide_password"
                    )
                }
            } else {
                IconButton(onClick = { showPassword.value = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = "show_password"
                    )
                }
            }
        },
        visualTransformation = if (showPassword.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        isError = passwordState.showErrors(),
        supportingText = {
            passwordState.getError()?.let { error -> TextFieldError(textError = error) }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = {}
        ),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedLabelColor = Color.Gray,
            unfocusedBorderColor = Color.Gray
        ),
        shape = RoundedCornerShape(16.dp)
    )
}

