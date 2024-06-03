package com.example.skills.ui.master.e

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.data.api.EditMasterRequest
import com.example.skills.data.entity.Address
import com.example.skills.data.roles.UserRequest
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.ui.components.CustomButton
import com.example.skills.ui.components.CustomOutlinedTextField
import com.example.skills.ui.components.Email
import com.example.skills.ui.components.ProfilePicturePicker
import com.example.skills.ui.components.spaceBetweenOutlinedTextField
import com.example.skills.ui.components.tools.EmailState
import com.example.skills.ui.components.tools.EmailStateSaver
import com.example.skills.ui.components.tools.LoadingScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavHostController,
    navigateToMain: () -> Unit,
    viewModel: MainViewModel
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
        val isLoading by viewModel.isLoading.collectAsState()
        if (isLoading) {
            LoadingScreen()
        } else {
            AddMasterAccountInfo(innerPadding, navigateToMain, viewModel, LocalContext.current)
        }
    }
}

@Composable
private fun AddMasterAccountInfo(
    innerPadding: PaddingValues,
    navigateToMain: () -> Unit,
    viewModel: MainViewModel,
    context: Context
) {
    val scrollState = rememberScrollState()
    val master = viewModel.currentUser!!

    val email by remember { mutableStateOf(master.email) }
    var firstName by remember { mutableStateOf(master.firstName) }
    var secondName by remember { mutableStateOf(master.lastName) }
    var phone by remember { mutableStateOf(master.phone) }

    val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
        mutableStateOf(EmailState(email))
    }
    var profileDescription by remember { mutableStateOf(checkAndInsert(master.master?.description)) }
    var address by remember { mutableStateOf(checkAndInsert(master.master?.address?.city.toString())) }
    var link by remember { mutableStateOf(checkAndInsert(master.master?.linkCode)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(
                top = innerPadding
                    .calculateTopPadding(),
                start = 16.dp,
                end = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ProfilePicturePicker(viewModel, LocalContext.current)
        Spacer(modifier = Modifier.height(6.dp))

        Text(text = "Изменить фото профиля", fontSize = 16.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(spaceBetweenOutlinedTextField.plus(12.dp)))
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
        Email(emailState, readOnly = true)

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
        OutlinedTextField(
            value = profileDescription,
            onValueChange = {
                profileDescription = it
            },
            label = { Text(text = "Описание профиля") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedLabelColor = Color.Gray,
                unfocusedBorderColor = Color.Gray
            ),
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = address,
            onValueChange = {
                address = it
            },
            label = { Text(text = "Адрес") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedLabelColor = Color.Gray,
                unfocusedBorderColor = Color.Gray
            ),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(spaceBetweenOutlinedTextField))
        OutlinedTextField(
            value = link,
            onValueChange = {
                link = it
            },
            label = { Text(text = "Ссылка") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedLabelColor = Color.Gray,
                unfocusedBorderColor = Color.Gray
            ),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            CustomButton(
                {
                    viewModel.editMasterProfile(
                        EditMasterRequest(
                            user = UserRequest(
                                firstName = firstName,
                                lastName = secondName,
                                phone = phone
                            ),
                            description = profileDescription,
                            address = Address(city = address),
                            linkCode = link // it`s messenger
                        ),
                        context = context
                    ) { successful ->
                        if (successful) {
                            navigateToMain.invoke()
                        }
                    }
                },
                "Сохранить"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

private fun checkAndInsert(text: String?): String {
    return if (text.isNullOrEmpty()) {
        ""
    } else {
        text
    }
}
