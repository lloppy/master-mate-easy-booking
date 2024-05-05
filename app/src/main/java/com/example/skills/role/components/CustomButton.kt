package com.example.skills.role.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skills.ui.theme.paddingBetweenElements

@Composable
fun CustomButton(
    navResId: String,
    navController: NavController,
    buttonText: String,
    height: Dp = buttonHeight,
    width: Float = 1f,
    color: Color = Color.Black
) {
    Button(
        onClick = { navController.navigate(navResId) },
        modifier = Modifier
            .fillMaxWidth(width)
            .height(height)
            .padding(start = paddingBetweenElements, end = paddingBetweenElements),
        shape = RoundedCornerShape(buttonRoundedCorner),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = if (color == Color.Black) Color.White else Color.Black
        ),
        border = BorderStroke(1.dp, Color.Black)

    ) {
        Text(text = buttonText)
    }
}


const val buttonRoundedCorner = 16



@Composable
fun CustomButton(
    navigateTo: () -> Unit,
    buttonText: String,
    height: Dp = buttonHeight,
    heightCoeff: Float = 1f,
    width: Float = 1f,
    color: Color = Color.Black,
    enabled: Boolean = true
) {
    Button(
        onClick = navigateTo,
        modifier = Modifier
            .fillMaxWidth(width)
            .height(height * heightCoeff)
            .padding(start = paddingBetweenElements, end = paddingBetweenElements),
        shape = RoundedCornerShape(buttonRoundedCorner),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = if (color == Color.Black) Color.White else Color.Black,
            disabledContainerColor = Color.White ,
            disabledContentColor = Color.Black
        ),
        enabled = enabled,
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Text(text = buttonText)
    }
}


var buttonHeight = 60.dp