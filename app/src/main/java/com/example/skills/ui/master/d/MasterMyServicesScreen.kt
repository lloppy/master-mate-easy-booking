package com.example.skills.ui.master.d

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.data.entity.Category
import com.example.skills.data.viewmodel.MY_LOG
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.navigation.ScreenRole
import com.example.skills.ui.components.CustomButton
import com.example.skills.ui.theme.paddingBetweenElements
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterMyServicesScreen(
    navigateToCreateCategory: () -> Unit,
    navigateToChangeCategory: () -> Unit,
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
        MasterMyServices(
            innerPadding,
            navigateToCreateCategory,
            navigateToChangeCategory,
            navController,
            viewModel
        )
    }
}


@Composable
fun MasterMyServices(
    innerPadding: PaddingValues,
    navigateToCreateCategory: () -> Unit,
    navigateToChangeCategory: () -> Unit,
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val receivedCategories by viewModel.categoriesLiveDataMaster.observeAsState()
    var selectedCategory by remember { mutableStateOf(if (receivedCategories?.isNotEmpty() == true) receivedCategories!!.first().name else "") }

    val categories = receivedCategories?.plus(
        Category(
            Int.MAX_VALUE,
            "Добавить категорию",
            description = "",
            action = navigateToCreateCategory
        )
    )
    val services by viewModel.servicesLiveDataMaster.observeAsState()
    Log.e(MY_LOG, "------services category name " + services?.first()?.name)
    Log.e(MY_LOG, "------services category id " + services?.first()?.category?.id)
    Log.e(MY_LOG, "------services category " + services?.first()?.category)

    Log.e(MY_LOG, "------services category name " + services?.last()?.name)
    Log.e(MY_LOG, "------services category id " + services?.last()?.category?.id)
    Log.e(MY_LOG, "------services category " + services?.last()?.category)
    //val selectedServicesByCategory = services?.filter { it.category.id == receivedCategories!!.first{it.name == selectedCategory}.id  }
//    val selectedCategoryID = receivedCategories?.firstOrNull{it?.name == selectedCategory}?.id
//    val selectedServicesByCategory = services?.filter { it.category?.id == selectedCategoryID  }

    Column(
        modifier = Modifier
            .padding(
                top = innerPadding.calculateTopPadding().plus(12.dp),
                bottom = 100.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            Modifier
                .weight(1f)
                .padding(
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            if (categories!!.size <= 1) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.23f)) {
                    Button(onClick = navigateToCreateCategory, Modifier.weight(1f)) {
                        Text("Добавить категорию")
                    }
                    Text(
                        text = "В вашем списке отсутствуют категории услуг. Чтобы добавить категорию, воспользуйтесь кнопкой выше.",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 12.dp, top = 25.dp)
                    )
                }
            } else {
                LazyRow(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {
                    items(categories) { category ->
                        val interactionSource = remember { MutableInteractionSource() }
                        val viewConfiguration = LocalViewConfiguration.current

                        LaunchedEffect(interactionSource) {
                            var isLongClick = false
                            interactionSource.interactions.collectLatest { interaction ->
                                when (interaction) {
                                    is PressInteraction.Press -> {
                                        isLongClick = false
                                        delay(viewConfiguration.longPressTimeoutMillis)
                                        isLongClick = true
                                        // categories = categories.filter { it.name != category.name }
                                        navigateToChangeCategory.invoke()
                                        // Toast.makeText(context, "Long click", Toast.LENGTH_SHORT).show()
                                    }

                                    is PressInteraction.Release -> {
                                        if (isLongClick.not()) {
                                            // Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            }
                        }
                        CategoryButton(
                            text = category.name,
                            onClick = {
                                if (category.name == "Добавить категорию") category.action.invoke() else category.action
                                selectedCategory = category.name
                            },
                            interactionSource = interactionSource,
                            containerColor = if (category.name == selectedCategory) Color.Black else Color.White,
                            contentColor = if (category.name == selectedCategory) Color.White else Color.Gray
                        )
                    }
                }

                if (services != null) {
                    LazyColumn {
                        items(services!!) { singleService ->
                            SingleServiceCard(singleService, navController)
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
        }
        if (categories!!.size > 1) {
            Spacer(modifier = Modifier.height(12.dp))
            CustomButton(
                navigateTo = {
                    try {
                        val selectedCategoryName = selectedCategory
                        Log.e(MY_LOG, "selectedCategoryName is $selectedCategoryName")

                        navController.navigate(
                            ScreenRole.Master.CreateServiceCard.route.replace(
                                "{selectedCategoryName}",
                                selectedCategoryName
                            )
                        )
                    } catch (e: IllegalArgumentException) { // нужно блин выбрать категорию, а не тыкать в пустоту 😤😤
                    }
                },
                buttonText = "Добавить услугу"
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