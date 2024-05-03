package com.example.skills.master.components.e

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skills.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterSettingsScreen(
    navigateToEditAccount: () -> Unit,
    navigateToEditPassword: () -> Unit,
    navigateToCalendar: () -> Unit,
    navigateToNotifications: () -> Unit,
    exit: () -> Unit
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
                        "Настройки",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            )
        },
    ) { innerPadding ->
        SettingsContent(
            innerPadding,
            navigateToEditAccount,
            navigateToEditPassword,
            navigateToCalendar,
            navigateToNotifications,
            exit
        )
    }
}

@Composable
private fun SettingsContent(
    innerPadding: PaddingValues,
    navigateToEditAccount: () -> Unit,
    navigateToEditPassword: () -> Unit,
    navigateToCalendar: () -> Unit,
    navigateToNotifications: () -> Unit,
    exit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            SettingsItem(
                R.drawable.settings_account,
                "Данные аккаунта",
                "Отредактируйте информацию",
                navigateToEditAccount
            )
            SettingsItem(
                R.drawable.settings_password,
                "Пароль",
                "Измените свой пароль",
                navigateToEditPassword
            )
            SettingsItem(
                R.drawable.settings_calendar,
                "Google Календарь",
                "Синхронизируйте расписание",
                navigateToCalendar
            )
            SettingsItem(
                R.drawable.settings_notification,
                "Уведомления",
                "Управляйте своими уведомлениями",
                navigateToNotifications
            )
        }
        TextButton(onClick = exit, modifier = Modifier.padding(bottom = 90.dp)) {
            Text(
                text = "Выйти",
                color = Color.Red,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun SettingsItem(icon: Int, title: String, desc: String, navigateTo: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "icon",
                    modifier = Modifier.height(52.dp)
                )
                Column(modifier = Modifier.padding(start = 12.dp)) {
                    Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = desc,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            IconButton(onClick = navigateTo) {
                Icon(Icons.Default.ArrowForwardIos, contentDescription = "icon")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(modifier = Modifier.fillMaxWidth(), color = Color.LightGray, thickness = 1.dp)
    }
}