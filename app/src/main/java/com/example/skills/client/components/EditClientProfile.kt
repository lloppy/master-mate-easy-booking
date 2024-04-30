package com.example.skills.client.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.role.components.CustomButton
import com.example.skills.role.components.CustomOutlinedTextField
import com.example.skills.role.components.Email
import com.example.skills.role.components.spaceBetweenOutlinedTextField
import com.example.skills.role.components.tools.EmailState
import com.example.skills.role.components.tools.EmailStateSaver


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditClientProfileScreen(
    navController: NavHostController,
    navigateToMain: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Данные аккаунта",
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
        EditClientAccountInfo(innerPadding, navigateToMain)
    }
}

@Composable
private fun EditClientAccountInfo(
    innerPadding: PaddingValues,
    navigateToMain: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var secondName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
        mutableStateOf(EmailState(email))
    }
    var birthday by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = innerPadding
                    .calculateTopPadding(),

                ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
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

            OutlinedTextField(
                value = birthday,
                onValueChange = { birthday = it },
                label = { Text(text = "Дата рождения (dd.MM.yyyy)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyMedium,
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedLabelColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray
                )
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
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(
            navigateToMain,
            "Сохранить",
            0.2f
        )
        Spacer(modifier = Modifier.height(16.dp))

    }
}
