package com.example.skills.master.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color.parseColor
import androidx.compose.foundation.clickable
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
import com.example.skills.role.components.CustomButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleCalendarScreen(navController: NavHostController, navigateToMain: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Google Календарь",
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
        }
    ) { innerPadding ->
        ContentGoogleCalendarInfo(innerPadding, navigateToMain)
    }
}


@Composable
fun ContentGoogleCalendarInfo(innerPadding: PaddingValues, navigateToMain: () -> Unit) {
    var calendarId by remember { mutableStateOf("") }
    val context = LocalContext.current
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = innerPadding
                    .calculateTopPadding()
                    .plus(12.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .padding(
                    start = 24.dp,
                    end = 24.dp
                ),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Добавьте наш сервисный аккаунт к вашему календарю:",
                lineHeight = lineHeight,
                fontSize = 14.sp, fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(spacerValue))

            Column {
                Text(
                    text = "1. Откройте Google Календарь через браузер.",
                    fontSize = instructionTextSize,
                    modifier = Modifier.padding(top = spacerValue)
                )
                Text(
                    text = "2. В левом меню найдите ваш календарь и кликните по трёхточечному меню рядом с его названием.",
                    lineHeight = lineHeight,
                    fontSize = instructionTextSize
                )
                Text(
                    text = "3. Выберите \"Настройки и общий доступ\".",
                    fontSize = instructionTextSize
                )
                Text(
                    text = "4. Пролистайте страницу до раздела \"Открыть доступ пользователям или группам\".",
                    lineHeight = lineHeight,
                    fontSize = instructionTextSize
                )
                Text(
                    text = "5. Нажмите \"Добавить пользователей или группы\".",
                    fontSize = instructionTextSize
                )
                Text(
                    text = "6. Введите адрес электронной почты нашего сервисного аккаунта",
                    lineHeight = lineHeight,
                    fontSize = instructionTextSize
                )
                Text(
                    text = "master@mate.gserviceaccount.com",
                    color = Color(parseColor("#007AFF")),
                    lineHeight = lineHeight,
                    fontWeight = FontWeight.Normal,
                    fontSize = instructionTextSize,
                    modifier = Modifier.clickable {
                        val clip =
                            ClipData.newPlainText("Copied Text", "master@mate.gserviceaccount.com")
                        clipboardManager.setPrimaryClip(clip)
                    }
                )

                Text(
                    text = "7. В разрешении укажите \"Внесение изменений и предоставление доступа\".",
                    fontSize = instructionTextSize,
                    lineHeight = lineHeight,
                )
                Text(text = "8. Нажмите \"Отправить\".", fontSize = instructionTextSize)
            }

            Spacer(modifier = Modifier.height(spacerValue))

            Text(
                text = "Найдите идентификатор вашего календаря:", lineHeight = lineHeight,
                fontSize = 14.sp, fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(spacerValue))

            Column {
                Text(
                    text = "1. В настройках вашего календаря (см. предыдущий пункт) перейдите к разделу \"Интеграция календаря\".",
                    lineHeight = lineHeight,
                    fontSize = instructionTextSize, modifier = Modifier.padding(top = spacerValue)
                )
                Text(
                    text = "2. Скопируйте идентификатор календаря и вставьте в поле ниже.",
                    lineHeight = lineHeight,
                    fontSize = instructionTextSize
                )
                Text(text = "3. Нажмите \"Сохранить\".", fontSize = instructionTextSize)
            }
            Spacer(modifier = Modifier.width(spacerValue))

            OutlinedTextField(
                value = calendarId,
                onValueChange = { calendarId = it },
                label = { Text(text = "Идентификатор календаря") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = spacerValue),
                textStyle = MaterialTheme.typography.bodyMedium,
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedLabelColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray
                )
            )
        }
        CustomButton(
            navigateToMain,
            "Сохранить"
        )
    }
}

var lineHeight = 18.sp
var spacerValue = 14.dp
var instructionTextSize = 14.sp