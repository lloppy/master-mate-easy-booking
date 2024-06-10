package com.example.skills.ui.client.a

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.skills.R
import com.example.skills.data.viewmodel.MainViewModel

@Composable
fun QRCodeScannerComposable(
    viewModel: MainViewModel,
    navController: NavHostController,
    onQrCodeScanned: (String) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted -> hasCameraPermission = granted }
    )

    LaunchedEffect(true) {
        launcher.launch(Manifest.permission.CAMERA)
    }

    if (hasCameraPermission) {
        val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

        AndroidView(factory = { ctx ->
            val previewView = PreviewView(ctx)

            val preview = Preview.Builder().build()
            val selector =
                CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
            preview.setSurfaceProvider(previewView.surfaceProvider)

            val imageAnalysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
            imageAnalysis.setAnalyzer(
                ContextCompat.getMainExecutor(ctx),
                QrCodeAnalyser(
                    context = ctx,
                    mainViewModel = viewModel,
                    onQrCodeScanner = onQrCodeScanned
                )
            )

            try {
                cameraProviderFuture.get()
                    .bindToLifecycle(lifecycleOwner, selector, preview, imageAnalysis)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            previewView
        }, modifier = Modifier.fillMaxSize())

        OverlayUI(navController, viewModel, context)
    }
}

@Composable
fun OverlayUI(navController: NavHostController, mainViewModel: MainViewModel, context: Context) {
    var mastersCode by remember { mutableStateOf("") }
    val screenSize = LocalConfiguration.current.screenHeightDp.dp / 2

    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp), contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.scan_card),
            contentDescription = "scan card",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = screenSize.minus(32.dp), start = 32.dp, end = 32.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp, start = 16.dp, end = 16.dp),
        ) {
            OutlinedTextField(
                value = mastersCode,
                onValueChange = { mastersCode = it },
                label = { Text(text = "Код мастера") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyMedium,
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedLabelColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedLabelColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                keyboardActions = KeyboardActions(onDone = {
                    try {
                        val masterId = mastersCode.substring(7).toInt()

                        mainViewModel.addMasterById(masterId) { successful ->
                            if (successful) {

                            } else {
                                Toast.makeText(
                                    context,
                                    "Ошибка сервера при получени master id: id is $masterId",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        mainViewModel.pushMasterById(masterId){ successful ->
//                            Toast.makeText(
//                                context,
//                                "Мастер добавлен. master id is $masterId",
//                                Toast.LENGTH_SHORT
//                            ).show()
                        }
                    } catch (e: Exception) {
                    }
                    navController.popBackStack()
                }),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            )
        }
    }
}
