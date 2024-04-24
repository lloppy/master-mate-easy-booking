package com.example.skills.master.components

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.example.skills.master.components.tools.PasswordState
import com.example.skills.ui.theme.backgroundMaterial

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPasswordScreen(
    navController: NavHostController,
    navigateToMain: () -> Unit
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
                        "Новый пароль",
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
        ContentNewPassword(innerPadding, navigateToMain)
    }
}

@Composable
fun ContentNewPassword(
    innerPadding: PaddingValues,
    navigateToMain: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val passwordState = remember { PasswordState() }
    val passwordStateRepeat = remember { PasswordState() }
    val onSubmit = {
        if (passwordState.isValid) {
            //onSignInSubmitted(emailState.text, passwordState.text)
            navigateToMain
        }
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
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "Придумайте новый пароль, он должен быть не короче 8 символов, содержать специальные знаки, заглавные буквы и цифры.",
                fontSize = 14.sp,
                modifier = Modifier.padding(16.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.26f)
                    .padding(start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Password(
                    label = "Пароль",
                    passwordState = passwordState,
                    modifier = Modifier.focusRequester(focusRequester),
                    onImeAction = { onSubmit() }
                )
                Password(
                    label = "Повторите пароль",
                    passwordState = passwordStateRepeat,
                    modifier = Modifier.focusRequester(focusRequester),
                    onImeAction = { onSubmit() }
                )

            }
            Spacer(modifier = Modifier.height(16.dp))

            CustomButton(
                navigateToMain,
                "Сохранить",
                0.14f
            )
        }
    }
}

