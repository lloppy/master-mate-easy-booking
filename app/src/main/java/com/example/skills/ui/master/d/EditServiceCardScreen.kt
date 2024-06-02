package com.example.skills.ui.master.d

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.data.entity.Duration
import com.example.skills.data.entity.EditServiceRequest
import com.example.skills.data.viewmodel.MY_LOG
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.ui.components.CustomButton
import com.example.skills.ui.components.CustomOutlinedTextField
import com.example.skills.ui.components.spaceBetweenOutlinedTextField
import com.example.skills.ui.components.tools.LoadingScreen
import com.example.skills.ui.theme.backgroundMaterial

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditServiceCardScreen(
    serviceName: String?,
    navController: NavHostController,
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
                        "Изменить услугу",
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
        val isLoading by viewModel.isLoading.collectAsState()
        if (isLoading) {
            LoadingScreen()
        } else {
            ContentEditServiceCard(innerPadding, navController, serviceName, viewModel)
        }
    }
}

@Composable
fun ContentEditServiceCard(
    innerPadding: PaddingValues,
    navController: NavHostController,
    serviceName: String?,
    viewModel: MainViewModel
) {
    Log.d(MY_LOG, "currentService name is $serviceName")

    val currentService = viewModel.getServiceByName(serviceName = serviceName)
    if (currentService == null) {
        Log.d(MY_LOG, "Service $serviceName not found")
    } else {
        var name by remember { mutableStateOf(currentService.name) }
        var price by remember { mutableStateOf(currentService.price.toString()) }
        var duration by remember { mutableStateOf(currentService.duration.toString()) }
        var description by remember { mutableStateOf(currentService.description) }

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
                    Text(text = "Категория: " + currentService.category.name)
                    Spacer(modifier = Modifier.height(8.dp))

                    CustomOutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = "Название"
                    )
                    Spacer(modifier = Modifier.height(spaceBetweenOutlinedTextField))

                    OutlinedTextField(
                        value = price,
                        onValueChange = { price = it },
                        label = { Text(text = "Цена в рублях") },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedLabelColor = Color.Gray,
                            unfocusedBorderColor = Color.Gray
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(16.dp)
                    )
                    Spacer(modifier = Modifier.height(spaceBetweenOutlinedTextField))

                    OutlinedTextField(
                        value = duration,
                        onValueChange = { duration = it },
                        label = { Text(text = "Длительность в минутах") },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedLabelColor = Color.Gray,
                            unfocusedBorderColor = Color.Gray
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(16.dp)
                    )
                    Spacer(modifier = Modifier.height(spaceBetweenOutlinedTextField))

                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text(text = "Описание") },
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
                    Spacer(modifier = Modifier.height(16.dp))
                }
                CustomButton(
                    navigateTo = {
                        try {
                            viewModel.editService(
                                EditServiceRequest(
                                    name = name,
                                    description = description,
                                    price = price.toInt(),
                                    duration = Duration(0, duration.toInt())
                                ),
                                currentService.id
                            ) { successful ->
                                if (successful) {
                                    navController.popBackStack()
                                } else {
                                    Log.e(MY_LOG, "editService not successful")
                                }
                            }
                        } catch (e: Exception) {
                            Log.e(MY_LOG, "editService Exception " + e.message.toString())
                        }
                    },
                    enabled = if (name.isNotEmpty() && description.isNotEmpty() && price.isNotEmpty() && duration.isNotEmpty()) true else false,
                    buttonText = "Сохранить"
                )
            }
        }
    }
}
