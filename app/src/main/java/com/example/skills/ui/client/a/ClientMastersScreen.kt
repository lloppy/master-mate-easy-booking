package com.example.skills.client.components.a

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityOptionsCompat
import com.example.skills.data.roles.Master
import com.example.skills.data.viewmodel.BookingViewModel
import com.example.skills.data.viewmodel.MyRepository.getMastersList
import com.example.skills.ui.components.QRCodeScannerScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientMastersScreen(
    bookingViewModel: BookingViewModel,
    navigateToSelectedMasterProfile: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Мастера",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        val intent = Intent(context, QRCodeScannerScreen::class.java)
                        val options =
                            ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity)
                        context.startActivity(intent, options.toBundle())
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "Localized description"
                        )
                    }
                })
        }
    ) { innerPadding ->
        ClientMastersContent(innerPadding, navigateToSelectedMasterProfile, bookingViewModel)
    }
}


@Composable
fun ClientMastersContent(
    innerPadding: PaddingValues,
    navigateToSelectedMasterProfile: () -> Unit,
    bookingViewModel: BookingViewModel
) {
    val emptyMasters = emptyList<Master>()
    val masters = getMastersList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (masters.isEmpty()) {
            Text(
                text = "Здесь будут отображаться ваши мастера. Чтобы добавить нового мастера, используйте ссылку, которую он вам предоставит.",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                modifier = Modifier.padding(start = 18.dp, end = 12.dp)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(masters) { master ->
                    SimpleMasterCard(master, navigateToSelectedMasterProfile, bookingViewModel)
                }
            }
        }
    }
}

