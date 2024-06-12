package com.example.skills.ui.master.d

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.ui.components.tools.LoadingScreen
import com.example.skills.ui.theme.paddingBetweenElements


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterMyServicesScreen(
    navigateToCreateCategory: () -> Unit,
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
                        "Мои услуги",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            )
        }
    ) { innerPadding ->
        val isLoading by viewModel.isLoading.collectAsState()
        if (isLoading) {
            LoadingScreen()
        } else {
            MasterMyServices(
                innerPadding,
                navigateToCreateCategory,
                navController,
                viewModel
            )
        }
    }
}

@Composable
fun CategoryButton(
    text: String,
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    interactionSource: MutableInteractionSource
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(45.dp)
            .padding(end = paddingBetweenElements),
        shape = RoundedCornerShape(40.dp),
        interactionSource = interactionSource,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        if (text == "Добавить категорию") {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier.padding(end = 6.dp)
            )
        }
        Text(text = text, fontSize = 14.sp, fontWeight = FontWeight.Normal)
    }
}