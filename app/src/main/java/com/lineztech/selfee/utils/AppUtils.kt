package com.lineztech.selfee.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Base64
import java.io.ByteArrayOutputStream


fun uriToBitmap(imageUri: Uri?, context: Context): Bitmap?{
    var bitmap: Bitmap? = null
    imageUri?.let {
        bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            ImageDecoder.decodeBitmap(source)
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, it)
        }
    }
    return bitmap
}


fun encodeImage(bm: Bitmap): String? {
    val byteArray = ByteArrayOutputStream()
    bm.compress(Bitmap.CompressFormat.JPEG, 100, byteArray)
    val b = byteArray.toByteArray()
    return Base64.encodeToString(b, Base64.DEFAULT)
}