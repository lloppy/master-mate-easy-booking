package com.example.skills.ui.master.a

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.skills.data.roles.User
import com.example.skills.data.viewmodel.MY_LOG
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.ui.components.getFileFromUri
import com.example.skills.ui.components.tools.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMasterScreen(
    navigateToShare: () -> Unit,
    viewModel: MainViewModel
) {
    val master = viewModel.currentUser
    val isLoading by viewModel.isLoading.collectAsState()

    if (isLoading || master == null) {
        LoadingScreen()
        return
    }

    if (master != null) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            "${master!!.firstName} ${master!!.lastName}",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    actions = {
                        IconButton(onClick = {
                            navigateToShare.invoke()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Share,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    navigationIcon = {
                        var selectedImage by remember { mutableStateOf<Uri?>(null) }
                        val context = LocalContext.current

                        val imagePickerLauncher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.GetContent()
                        ) { uri: Uri? ->
                            selectedImage = uri

                            selectedImage?.let { uriNonNull ->
                                val file = getFileFromUri(context, uriNonNull)
                                if (file != null) {
                                    viewModel.uploadWorkPicture(file, context)
                                } else {
                                    Log.e(MY_LOG, "File conversion failed")
                                }
                            }
                        }
                        IconButton(onClick = {
                            imagePickerLauncher.launch("image/*")
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                )
            },
        ) { innerPadding ->
            val isLoading by viewModel.isLoading.collectAsState()
            if (isLoading) {
                LoadingScreen()
            } else {
                MasterHomeScreen(
                    innerPadding,
                    master,
                    viewModel
                )
            }
        }
    }
}

@Composable
fun MasterHomeScreen(
    innerPadding: PaddingValues,
    user: User,
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ProfileHead(user)
        if (user.master?.images != null) {
            val isLoading by viewModel.isLoading.collectAsState()
            if (isLoading) {
                LoadingScreen()
            } else {
                MasterGallery(user.master!!.images!!)
            }
        }
    }
}