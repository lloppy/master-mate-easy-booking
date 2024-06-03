package com.example.skills.ui.master.d

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.data.entity.Category
import com.example.skills.data.entity.CategoryRequest
import com.example.skills.data.viewmodel.MY_LOG
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.ui.components.CustomButton
import com.example.skills.ui.components.CustomOutlinedTextField
import com.example.skills.ui.components.tools.LoadingScreen
import com.example.skills.ui.theme.backgroundMaterial

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeCategoryScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    selectedCategory: Category
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
                        "Изменить категорию",
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
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                tint = Color.Black,
                                contentDescription = "Localized description"
                            )
                        }
                    }

                }
            )
        },
    ) { innerPadding ->
        val isLoading by viewModel.isLoading.collectAsState()
        if (isLoading) {
            LoadingScreen()
        } else {
            ContentChangeCategory(innerPadding, navController, viewModel, selectedCategory)
        }
    }
}

@Composable
fun ContentChangeCategory(
    innerPadding: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel,
    selectedCategory: Category
) {
    var categoryName by remember { mutableStateOf(selectedCategory.name) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundMaterial)
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 8.dp, end = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                CustomOutlinedTextField(
                    value = categoryName,
                    onValueChange = { categoryName = it },
                    label = "Название"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(
                navigateTo = {
                    viewModel.editCategory(
                        selectedCategory.id,
                        CategoryRequest(name = categoryName, description = "")
                    ) { successful ->
                        if (successful) {
                            navController.popBackStack()
                        }
                    }
                },
                "Сохранить"
            )

            Spacer(modifier = Modifier.height(8.dp))
            CustomButton(
                navigateTo = {
                    try {
                        viewModel.deleteCategory(categoryId = selectedCategory.id) { successful ->
                            if (successful) {
                                navController.popBackStack()
                            }
                        }
                    } catch (e: Exception) {
                        Log.e(MY_LOG, "deleteService Exception " + e.message.toString())
                    }
                },
                color = Color.Transparent,
                buttonText = "Удалить"
            )
        }
    }
}