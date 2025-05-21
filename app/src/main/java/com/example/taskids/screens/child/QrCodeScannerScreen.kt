package com.example.taskids.screens.child

import android.Manifest
import android.content.pm.PackageManager
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*

@Composable
fun QRCodeScannerScreen(onCodeScanned: (String) -> Unit) {
    val context = LocalContext.current
    val activity = context as ComponentActivity

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasCameraPermission = granted
    }

    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            launcher.launch(Manifest.permission.CAMERA)
        }
    }

    if (hasCameraPermission) {
        AndroidView(
            modifier = Modifier,
            factory = { ctx ->
                val scannerView = CodeScannerView(ctx)
                val scanner = CodeScanner(ctx, scannerView)

                scanner.camera = CodeScanner.CAMERA_BACK
                scanner.formats = CodeScanner.ALL_FORMATS
                scanner.autoFocusMode = AutoFocusMode.SAFE
                scanner.scanMode = ScanMode.SINGLE
                scanner.isAutoFocusEnabled = true
                scanner.isFlashEnabled = false

                scanner.decodeCallback = DecodeCallback {
                    activity.runOnUiThread {
                        onCodeScanned(it.text)
                    }
                }

                scanner.errorCallback = ErrorCallback {
                    println("Camera error: ${it.message}")
                }

                scanner.startPreview()
                scannerView
            }
        )
    }
}
