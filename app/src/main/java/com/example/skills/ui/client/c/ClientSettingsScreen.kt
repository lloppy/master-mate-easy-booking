package com.example.skills.ui.client.c

import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.data.api.EditClientRequest
import com.example.skills.data.roles.ClientRequest
import com.example.skills.data.viewmodel.MY_LOG
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.ui.components.CustomButton
import com.example.skills.ui.components.CustomOutlinedTextField
import com.example.skills.ui.components.Email
import com.example.skills.ui.components.spaceBetweenOutlinedTextField
import com.example.skills.ui.components.tools.EmailState
import com.example.skills.ui.components.tools.EmailStateSaver
import com.example.skills.ui.components.tools.Validator.isBirthDateValid
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditClientProfileScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
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
        EditClientAccountInfo(innerPadding, viewModel, navigateToMain)
    }
}

@Composable
private fun EditClientAccountInfo(
    innerPadding: PaddingValues,
    viewModel: MainViewModel,
    navigateToMain: () -> Unit
) {
    val client = viewModel.currentUser!!
    val context = LocalContext.current

    val email by remember { mutableStateOf(client.email) }
    var firstName by remember { mutableStateOf(client.firstName) }
    var secondName by remember { mutableStateOf(client.lastName) }
    var phone by remember { mutableStateOf(client.phone) }
    var birthday by remember { mutableStateOf(if (client.client?.birthday != null) client.client!!.birthday!! else "") }

    val focusRequester = remember { FocusRequester() }
    val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
        mutableStateOf(EmailState(email))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
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

            Spacer(modifier = Modifier.height(spaceBetweenOutlinedTextField))

            OutlinedTextField(
                value = birthday,
                onValueChange = { birthday = it },
                label = { Text(text = "Дата рождения (dd.MM.yyyy)") },
                keyboardOptions = KeyboardOptions(
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
            Spacer(modifier = Modifier.height(spaceBetweenOutlinedTextField))
            Email(emailState, readOnly = true, onImeAction = { focusRequester.requestFocus() })

            Spacer(modifier = Modifier.height(spaceBetweenOutlinedTextField))
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text(text = "Номер телефона") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                textStyle = MaterialTheme.typography.bodyMedium,
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedLabelColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray
                ),
                shape = RoundedCornerShape(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(
            {
                if (isBirthDateValid(birthday)) {
                    val sourceFormat =
                        SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                    val desiredFormat =
                        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

                    val date = sourceFormat.parse(birthday)

                    viewModel.editClientProfile(
                        EditClientRequest(
                            user = ClientRequest(
                                firstName = firstName,
                                lastName = secondName,
                                email = email,
                                phone = phone
                            ),
                            birthDate = desiredFormat.format(date!!).toString()
                        ),
                        context = context
                    ) { successful ->
                        if (successful) {
                            navigateToMain.invoke()
                        }
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Введите дату рождения в формате dd.MM.yyyy",
                        Toast.LENGTH_LONG
                    ).show()
                }
            },
            "Сохранить"
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
