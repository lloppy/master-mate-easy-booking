package com.example.skills.general.components

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraSelector.LENS_FACING_BACK
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import com.example.skills.R
import com.example.skills.general.components.tools.QrCodeAnalyser
import com.example.skills.ui.theme.SkillsTheme

class QRCodeScannerScreen : ComponentActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkillsTheme {
                val context = LocalContext.current

                var code by remember { mutableStateOf("") }
                var mastersCode by remember { mutableStateOf("") }
                val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
                val lifecycleOwner = LocalLifecycleOwner.current

                var hasCameraPermission = remember {
                    ContextCompat.checkSelfPermission(
                        context, android.Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                }
                val launcher =
                    rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestPermission(),
                        onResult = { granted ->
                            hasCameraPermission = granted
                        })

                LaunchedEffect(key1 = true) {
                    launcher.launch(android.Manifest.permission.CAMERA)
                }

                val screenSize = LocalConfiguration.current.screenHeightDp.dp / 2
                Box(
                    Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
                ) {
                    if (hasCameraPermission) {
                        AndroidView(
                            factory = { context ->
                                val previewView = PreviewView(context)
                                val preview = Preview.Builder().build()
                                val selector = CameraSelector.Builder().requireLensFacing(
                                    LENS_FACING_BACK
                                ).build()
                                preview.setSurfaceProvider(previewView.surfaceProvider)
                                val imageAnalysis = ImageAnalysis.Builder()
                                    .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST).build()
                                imageAnalysis.setAnalyzer(
                                    ContextCompat.getMainExecutor(context),
                                    QrCodeAnalyser(
                                        activity = this@QRCodeScannerScreen,
                                        context = context
                                    ) { result ->
                                        code = result
                                        //    createBillFromQR(code)

                                    })
                                try {
                                    cameraProviderFuture.get().bindToLifecycle(
                                        lifecycleOwner, selector, preview, imageAnalysis
                                    )
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                                previewView
                            }, modifier = Modifier.fillMaxSize()
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.scan_card),
                        contentDescription = "scan card",
                        modifier = Modifier.fillMaxWidth().padding(bottom = screenSize.minus(32.dp), start = 32.dp, end = 32.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 48.dp, start = 16.dp, end = 16.dp),
                    ) {
                        CustomOutlinedTextField(
                            value = mastersCode,
                            onValueChange = { mastersCode = it },
                            label = "Код мастера",
                            isCameraInput = true
                        )
                    }
                }
            }
        }
    }
}