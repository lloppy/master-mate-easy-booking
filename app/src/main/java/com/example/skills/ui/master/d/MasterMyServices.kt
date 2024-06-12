package com.example.skills.ui.master.d

import android.util.Log
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.data.entity.Category
import com.example.skills.data.entity.Service
import com.example.skills.data.viewmodel.MainViewModel

@Composable
fun MasterMyServices(
    innerPadding: PaddingValues,
    navigateToCreateCategory: () -> Unit,
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val receivedCategories by viewModel.categoriesLiveDataMaster.observeAsState()
    var selectedCategory by remember { mutableStateOf(if (receivedCategories?.isNotEmpty() == true) receivedCategories!!.first().name else "") }
    val categories = receivedCategories?.plus(
        Category(
            Int.MAX_VALUE, "Добавить категорию",
            description = "", action = navigateToCreateCategory
        )
    )
    val services by viewModel.servicesLiveDataMaster.observeAsState()

    Column(
        modifier = Modifier
            .padding(top = innerPadding.calculateTopPadding().plus(12.dp), bottom = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            if (categories != null) {
                when {
                    categories.size <= 1 -> EmptyCategoriesView(
                        navigateToCreateCategory,
                        viewModel
                    )

                    else -> CategoriesWithServicesView(
                        categories,
                        selectedCategory,
                        viewModel,
                        navController,
                        services
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyCategoriesView(
    navigateToCreateCategory: () -> Unit,
    viewModel: MainViewModel
) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.23f)) {
            CreateCategoryButton(navigateToCreateCategory)
            Text(
                text = "В вашем списке отсутствуют категории услуг. Чтобы добавить категорию, воспользуйтесь кнопкой выше.",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                modifier = Modifier.padding(start = 12.dp, top = 25.dp)
            )
        }
    }


@Composable
fun CreateCategoryButton(navigateToCreateCategory: () -> Unit) {
    Button(onClick = navigateToCreateCategory) {
        Text("Добавить категорию")
    }
}

@Composable
fun CategoriesWithServicesView(
    categories: List<Category>,
    selectedCategory: String,
    viewModel: MainViewModel,
    navController: NavHostController,
    services: List<Service>?,
) {
    var selectedCategoryName by remember { mutableStateOf(selectedCategory) }

    LazyRow(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        items(categories) { category ->
            CategoryButton(
                text = category.name,
                onClick = {
                    if (category.name == "Добавить категорию") category.action.invoke() else category.action
                    selectedCategoryName = category.name
                },
                interactionSource = remember { MutableInteractionSource() },
                containerColor = if (category.name == selectedCategoryName) Color.Black else Color.White,
                contentColor = if (category.name == selectedCategoryName) Color.White else Color.Gray
            )
        }
    }

    val selectedServicesByCategory = services?.filter { it.category.name == selectedCategoryName }

    if (selectedServicesByCategory != null && categories.size > 1) {
        LazyColumn {
            items(selectedServicesByCategory) { singleService ->
                SingleServiceCard(singleService, navController, viewModel)
            }
        }
    } else {
        Text(
            text = "В этой категории пока нет услуг. \nЧтобы создать их, воспользуйтесь кнопкой ниже.",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            modifier = Modifier.padding(start = 12.dp, top = 25.dp)
        )
    }

}