package com.example.skills.master.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skills.R
import com.example.skills.role.components.CustomButton
import com.example.skills.ui.theme.paddingBetweenElements

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterMyServicesScreen(
    navigateToCreateCategory: () -> Unit,
    navigateToChangeCategory: () -> Unit
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
        MasterMyServices(innerPadding, navigateToCreateCategory, navigateToChangeCategory)
    }
}

@Composable
fun MasterMyServices(
    innerPadding: PaddingValues,
    navigateToCreateCategory: () -> Unit,
    navigateToChangeCategory: () -> Unit
) {
    var selectedCategory by remember { mutableStateOf("") }
    val categories by remember {
        mutableStateOf(
            listOf<Category>(
                Category(
                    "Категория 1",
                    {},
                    singlesCategory = listOf(
                        SingleService(
                            "Маникюр классический",
                            "Процесс включает в себя увлажнение и массаж рук, обработку кутикулы, подпиливание и придание им красивой формы, удаление кутикулы, нанесение крема для ухода за руками и масла для ухода за кутикулой.",
                            800,
                            45
                        ),
                        SingleService(
                            "Маникюр европейский",
                            "Кутикула аккуратно отодвигается специальным апельсиновым палочкой или мягким пушером. Чтобы размягчить грубую кутикулу, ее можно регулярно смазывать",
                            1000,
                            55
                        ),
                        SingleService(
                            "Маникюр классический",
                            "Процесс включает в себя увлажнение и массаж рук, обработку кутикулы, подпиливание и придание им красивой формы, удаление кутикулы, нанесение крема для ухода за руками и масла для ухода за кутикулой.",
                            800,
                            45
                        )
                    )
                ),
                Category(
                    "Категория 2", {},
                    singlesCategory = listOf(
                        SingleService(
                            "Педикюр",
                            "Расслабляющая ванночка для ног, обработка кутикулы, коррекция формы ногтей, удаление огрубевшей кожи",
                            1200,
                            75
                        )
                    )
                ),
                Category("Категория 3", {}),
                Category("Добавить категорию", navigateToCreateCategory)
            )
        )
    }

    Column(
        modifier = Modifier
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding().plus(12.dp)
            )
    ) {
        if (categories.size <= 1) {
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
        } else {
            LazyRow(Modifier.fillMaxWidth()) {
                items(categories) { category ->
                    CategoryButton(
                        text = category.name,
                        onClick = {
                            category.action.invoke()
                            selectedCategory = category.name
                        },
                        containerColor = if (category.name == selectedCategory) Color.Black else Color.White,
                        contentColor = if (category.name == selectedCategory) Color.White else Color.Gray
                    )
                }
            }
            val selectedCategoryServices =
                categories.find { it.name == selectedCategory }?.singlesCategory

            if (selectedCategoryServices != null) {
                LazyColumn(modifier = Modifier.padding(bottom = 100.dp)) {
                    items(selectedCategoryServices) { singleService ->
                        SingleServiceCard(singleService)
                    }
                }
            } else {
                Text(
                    text = "В этой категории пока нет услуг. Чтобы создать их, нажмите на иконку с плюсом в левом верхнем углу.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 12.dp, top = 25.dp)
                )
            }

        }
    }
}

@Composable
fun SingleServiceCard(singleService: SingleService) {
    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .width(500.dp)
            .height(250.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Black),
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = singleService.name,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(paddingBetweenText))
            Text(
                text = singleService.price.toString() + " руб",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(paddingBetweenText))
            Text(
                text = singleService.duration.toString() + " мин",
                color = Color.White,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(paddingBetweenText))
            Text(
                text = "Описание: " + singleService.description,
                color = Color.LightGray,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                maxLines = 3,
                lineHeight = lineHeight
            )
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    CustomButton(navigateTo = { /*TODO*/ }, buttonText = "Изменить", width = 0.6f)
                }
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.archive),
                            contentDescription = "archive",
                            tint = Color.White,
                        )
                    }
                }
                Box(
                    modifier = Modifier.border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bin),
                            contentDescription = "bin",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

val paddingBetweenText = 8.dp


@Composable
fun CategoryButton(
    text: String,
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(45.dp)
            .padding(end = paddingBetweenElements),
        shape = RoundedCornerShape(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        border = BorderStroke(1.dp, Color.Gray)

    ) {
        Text(text = text, fontSize = 14.sp, fontWeight = FontWeight.Normal)
    }
}


data class Category(
    val name: String,
    var action: () -> Unit,
    val singlesCategory: List<SingleService>? = null
)

data class SingleService(
    var name: String,
    var description: String,
    var price: Int,
    var duration: Int
)

