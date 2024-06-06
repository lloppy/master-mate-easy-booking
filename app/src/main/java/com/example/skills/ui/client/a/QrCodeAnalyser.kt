package com.example.skills.ui.client.a

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.ImageFormat
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.skills.data.viewmodel.MY_LOG
import com.example.skills.data.viewmodel.MainViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.PlanarYUVLuminanceSource
import com.google.zxing.common.HybridBinarizer
import java.nio.ByteBuffer

class QrCodeAnalyser(
    private val activity: Activity,
    private val context: Context,
    mainViewModel: MainViewModel,
    private val onQrCodeScanner: (String) -> Unit,
) : ImageAnalysis.Analyzer {
    private val supportedImageFormats = listOf(
        ImageFormat.YUV_420_888, ImageFormat.YUV_422_888, ImageFormat.YUV_444_888
    )

    @SuppressLint("NewApi")
    override fun analyze(image: ImageProxy) {
        if (image.format in supportedImageFormats) {
            val bytes = image.planes.first().buffer.toByteArray()
            val source = PlanarYUVLuminanceSource(
                bytes, image.width, image.height, 0, 0, image.width, image.height, false
            )
            val binaryBmp = BinaryBitmap(HybridBinarizer(source))

            try {
                val result = MultiFormatReader().apply {
                    setHints(
                        mapOf(
                            DecodeHintType.POSSIBLE_FORMATS to arrayListOf(
                                BarcodeFormat.QR_CODE
                            )
                        )
                    )
                }.decode(binaryBmp)
                onQrCodeScanner(result.text)
                Log.e(MY_LOG, "result in camera is ${result.text}")

                try {
                    image.close()
                    activity.finish()
                } catch (e: IllegalArgumentException) {
                    Toast.makeText(context, "Не получилось добавить мастера", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                image.close()
            }
        }
    }

    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()
        return ByteArray(remaining()).also {
            get(it)
        }
    }
}