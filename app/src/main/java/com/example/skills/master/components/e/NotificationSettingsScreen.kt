package com.example.skills.master.components.e

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationSettingsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Уведомления",
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
        ContentNotificationSettings(innerPadding)
    }
}


@Composable
fun ContentNotificationSettings(innerPadding: PaddingValues) {
    var isNotificationEnabled by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = innerPadding.calculateTopPadding(),
                start = 24.dp,
                end = 24.dp
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Уведомления по Email",
                lineHeight = lineHeight,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Switch(
                checked = isNotificationEnabled,
                onCheckedChange = {
                    isNotificationEnabled = it
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(parseColor("#00AB67")),
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.LightGray,
                    uncheckedBorderColor = Color.LightGray
                )
            )
        }

        Text(
            text = "Уведомления в Telegram",
            lineHeight = lineHeight,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 32.dp)
        )
        Spacer(modifier = Modifier.width(spacerValue))

        Column {
            Text(
                text = "Для того чтобы начать получать уведомления о записях через Telegram, выполните следующие шаги:",
                fontSize = instructionTextSize,
                lineHeight = lineHeight,
                modifier = Modifier.padding(top = spacerValue)
            )

            Text(
                text = "1. Запустите приложение Telegram на вашем устройстве.",
                lineHeight = lineHeight,
                fontSize = instructionTextSize,
                modifier = Modifier.padding(top = 2.dp)
            )
            Text(
                text = "2. Нажмите на название нашего бота",
                fontSize = instructionTextSize
            )
            Text(
                text = "@mastermate_bot",
                color = Color(parseColor("#007AFF")),
                lineHeight = lineHeight,
                fontWeight = FontWeight.Normal,
                fontSize = instructionTextSize,
                modifier = Modifier.clickable {
                    val clip =
                        ClipData.newPlainText("Copied Text", "@mastermate_bot")
                    clipboardManager.setPrimaryClip(clip)
                }
            )
            Text(
                text = "или введите его в поисковую строку Telegram.",
                fontSize = instructionTextSize,
                lineHeight = lineHeight,
            )
            Text(
                text = "3. Скопируйте следующее сообщение «Тут какое-то сообщение» и отправьте его нашему боту.",
                fontSize = instructionTextSize,
                lineHeight = lineHeight,
                modifier = Modifier.clickable {
                    val clip =
                        ClipData.newPlainText("Copied Text", "Тут какое-то сообщение")
                    clipboardManager.setPrimaryClip(clip)
                }
            )
            Text(
                text = "4. После отправки сообщения, бот подтвердит вашу подписку и вы начнете получать уведомления.",
                fontSize = instructionTextSize,
                lineHeight = lineHeight,
            )
        }
    }
}
