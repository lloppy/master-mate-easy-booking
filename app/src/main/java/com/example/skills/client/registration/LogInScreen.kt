package com.example.skills.client.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.skills.navigation.client.registration.Screen
import com.example.skills.ui.theme.backgroundMaterial
import com.example.skills.ui.theme.paddingBetweenElements
import com.example.skills.ui.theme.spacer

@Composable
fun LogInScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundMaterial)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Вход",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(8.dp)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Почта") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(spacer))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Пароль") },

                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (password != "123") {
                        navController.navigate(Screen.MainMasterLayout.route)
                    } else {
                        navController.navigate(Screen.MainClientLayout.route)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(paddingBetweenElements),
                shape = RoundedCornerShape(40)
            ) {
                Text("Войти")
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("или")
                TextButton(onClick = {
                    navController.navigate(route = Screen.ClientRegistration.route)
                }) {
                    Text("Зарегестрироваться")
                }
            }
        }
    }
}
