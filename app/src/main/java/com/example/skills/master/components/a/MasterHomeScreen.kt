package com.example.skills.master.components.a

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Add
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.skills.R
import com.example.skills.master.components.b.calendar.clickable
import com.example.skills.master.components.d.paddingBetweenText
import com.example.skills.repository.MainViewModel
import com.example.skills.ui.theme.fontFamilyInter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMasterScreen(
    navController: NavHostController
) {
    val mainViewModel = MainViewModel()
    val master = mainViewModel.getMaster()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "${master.firstName} ${master.lastName}",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Localized description"
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        MasterHomeScreen(
            innerPadding,
            navController
        )
    }
}


@Composable
fun MasterHomeScreen(
    innerPadding: PaddingValues,
    navController: NavHostController
) {
    val mainViewModel = MainViewModel()
    val master = mainViewModel.getMaster()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.22f)
                .padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            val context = LocalContext.current
            val imageName = master.imageId ?: "master"
            val imageId =
                context.resources.getIdentifier(imageName, "drawable", context.packageName)
            val painter =
                if (imageId != 0) painterResource(id = imageId) else painterResource(id = R.drawable.master)

            Image(
                painter = painter,
                contentDescription = "Master image",
                Modifier.fillMaxSize(),
                alignment = Alignment.Center
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 8.dp, end = 8.dp, bottom = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            var expanded by remember { mutableStateOf(false) }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = master.description,
                    fontSize = 14.sp,
                    maxLines = if (expanded) Int.MAX_VALUE else 3,
                    fontFamily = fontFamilyInter,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
//                lineHeight = 18.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp)
                )
                if (!expanded) {
                    Text(
                        text = "... ещё",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .clickable {
                                expanded = !expanded
                            })
                }

            }
            if (expanded) {
                Spacer(modifier = Modifier.height(paddingBetweenText))
                Text(
                    text = master.address,
                    fontSize = 14.sp,
                    fontFamily = fontFamilyInter,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
//                lineHeight = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(paddingBetweenText))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_link),
                    contentDescription = "link",
                    tint = Color(0, 122, 255),
                )
                val context = LocalContext.current
                val uri = Uri.parse(master.linkCode)
                val intent = Intent(Intent.ACTION_VIEW, uri)

                Text(
                    text = master.linkCode,
                    fontSize = 14.sp,
                    fontFamily = fontFamilyInter,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
//                lineHeight = 18.sp,
                    color = Color(0, 122, 255),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable {
                            context.startActivity(intent)
                        }
                )
                Spacer(modifier = Modifier.height(paddingBetweenText))
            }
            MasterGallery(master.images)
        }
    }
}