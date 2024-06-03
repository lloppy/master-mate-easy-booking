package com.example.skills.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    width: Float = 1f,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    readOnly: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(width),
        textStyle = MaterialTheme.typography.bodyMedium,
        singleLine = true,
        colors = if (readOnly) {
            OutlinedTextFieldDefaults.colors(
                unfocusedLabelColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray,
                unfocusedTextColor = Color.LightGray,
                disabledTextColor = Color.LightGray
            )
        } else {
            OutlinedTextFieldDefaults.colors(
                unfocusedLabelColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedLabelColor =  Color.Black
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        interactionSource = interactionSource,
        readOnly = readOnly,
        shape = RoundedCornerShape(16.dp)
    )
}