package com.example.taskids.screens.parent

import android.graphics.Bitmap
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.drawToBitmap
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.core.app.ActivityCompat
import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.provider.MediaStore
import com.example.taskids.components.CustomButton
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.taskids.R
import java.io.OutputStream

@Composable
fun ParentChildQRCode(navController: NavController) {
    val context = LocalContext.current
    val activity = context as Activity
    val view = LocalView.current

    val qrLayoutRef = remember { mutableStateOf<Bitmap?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.White)
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.qrcode), // Substitua por seu QR real
                contentDescription = "QRCode",
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(32.dp))


        CustomButton(
            onClick = {
                val bitmap = view.drawToBitmap()
                val filename = "QRCode_${System.currentTimeMillis()}.jpg"

                val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
                if (PermissionChecker.checkSelfPermission(context, permission) != PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, arrayOf(permission), 0)
                }

                saveBitmapToGallery(context, bitmap, filename)
                Toast.makeText(context, "QR Code salvo na galeria!", Toast.LENGTH_LONG).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(10.dp),
            label = "Salvar!"
        )
    }
}

fun saveBitmapToGallery(context: android.content.Context, bitmap: Bitmap, filename: String) {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, filename)
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/TaskKids")
        put(MediaStore.Images.Media.IS_PENDING, 1)
    }

    val resolver = context.contentResolver
    val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    uri?.let {
        val stream: OutputStream? = resolver.openOutputStream(it)
        stream?.let { it1 -> bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it1) }
        stream?.close()
        contentValues.clear()
        contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
        resolver.update(uri, contentValues, null, null)
    }
}
