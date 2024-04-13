package com.example.skills.client.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.skills.navigation.Screen
import com.example.skills.ui.theme.paddingBetweenElements
import com.example.skills.ui.theme.spacer

@Composable
fun LogInScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Вход",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Почта") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(spacer))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* TODO: Handle login */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingBetweenElements)
        ) {
            Text("Войти")
        }
        Spacer(modifier = Modifier.height(spacer))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("или")
            TextButton(onClick = {
                navController.navigate(route = Screen.Registration.route)
            }) {
                Text("Зарегестрироваться")
            }
        }
    }
}
