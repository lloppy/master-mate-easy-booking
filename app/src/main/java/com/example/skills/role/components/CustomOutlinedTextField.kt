package com.example.skills.role.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    width: Float = 1f
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(width),
        textStyle = MaterialTheme.typography.bodyMedium,
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedLabelColor = Color.Gray,
            unfocusedBorderColor = Color.Gray
        )
    )
}