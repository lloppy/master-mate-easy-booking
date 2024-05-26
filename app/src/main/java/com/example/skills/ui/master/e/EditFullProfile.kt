package com.example.skills.ui.master.e

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.skills.ui.components.CustomButton
import com.example.skills.ui.components.CustomOutlinedTextField
import com.example.skills.ui.components.Email
import com.example.skills.ui.components.spaceBetweenOutlinedTextField
import com.example.skills.ui.components.tools.EmailState
import com.example.skills.ui.components.tools.EmailStateSaver


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
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
        AddMasterAccountInfo(innerPadding, navigateToMain, navController)
    }
}

@Composable
private fun AddMasterAccountInfo(
    innerPadding: PaddingValues,
    navigateToMain: () -> Unit,
    navController: NavHostController
) {
    val scrollState = rememberScrollState()

    var email by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var secondName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
        mutableStateOf(EmailState(email))
    }
    var profileDescription by remember { mutableStateOf("") }
    var adress by remember { mutableStateOf("") }
    var link by remember { mutableStateOf("") }

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
        ProfilePicturePicker()
        Spacer(modifier = Modifier.height(6.dp))

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Изменить фото профиля", fontSize = 16.sp, color = Color.Gray)
        }
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
            )
        )
        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = adress,
            onValueChange = {
                adress = it
            },
            label = { Text(text = "Адрес") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedLabelColor = Color.Gray,
                unfocusedBorderColor = Color.Gray
            )
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
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            CustomButton(
                // { navController.popBackStack() },
                navigateToMain,
                "Сохранить"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
fun ProfilePicturePicker() {
    var selectedImage by remember { mutableStateOf<Uri?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImage = uri
    }

    Column {
        if (selectedImage != null) {
            Image(
                painter = rememberAsyncImagePainter(selectedImage),
                contentDescription = "Selected profile pic",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .clip(CircleShape)
            )
        } else {
            IconButton(
                onClick = { imagePickerLauncher.launch("image/*") },
                colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Black),
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray)
            ) {
                Icon(
                    Icons.Default.PhotoCamera,
                    "Добавьте фото профиля",
                    tint = Color.White
                )
            }
        }
    }
}
