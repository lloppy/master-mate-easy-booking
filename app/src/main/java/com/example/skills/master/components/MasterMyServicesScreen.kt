package com.example.skills.master.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.skills.R
import com.example.skills.ui.theme.paddingBetweenElements
import androidx.compose.runtime.State
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterMyServicesScreen(
    navigateToCreateCategory: () -> Unit
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
                },
                navigationIcon = {
                    Row {
                        IconButton(onClick = navigateToCreateCategory) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                tint = Color.Black,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.archive),
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        MasterMyServices(innerPadding, navigateToCreateCategory)
    }
}

@Composable
fun MasterMyServices(
    innerPadding: PaddingValues,
    navigateToCreateCategory: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding().plus(12.dp)
            )
    ) {
        val scrollState = rememberScrollState()
        val categories by remember { mutableStateOf(listOf<String>()) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            categories.forEachIndexed { index, category ->
                CategoryButton(
                    text = category,
                    onClick = {}
                )
            }
            CategoryButton("Добавить категорию", navigateToCreateCategory)
        }

        if (categories.isEmpty()) {
            Text(
                text = "В вашем списке отсутствуют категории услуг. Чтобы добавить категорию, воспользуйтесь кнопкой выше.",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                modifier = Modifier.padding(start = 12.dp, top = 25.dp)
            )
        }
    }
}


@Composable
fun CategoryButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(45.dp)
            .padding(end = paddingBetweenElements),
        shape = RoundedCornerShape(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Gray
        ),
        border = BorderStroke(1.dp, Color.Gray)

    ) {
        Text(text = text, fontSize = 14.sp, fontWeight = FontWeight.Normal)
    }
}
