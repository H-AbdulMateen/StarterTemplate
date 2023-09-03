package com.abdulmateen.startertemplate.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


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

fun formatDateTimeStamp(timestamp: Long, pattern: String): String? {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val instant = Instant.ofEpochMilli(timestamp)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    return formatter.format(localDateTime)
//    return DateTimeFormatter.ofPattern(
//        "yyyy MM dd",
//        Locale.getDefault()
//    ).format(Instant.ofEpochSecond(timestamp))
//    val date = LocalDate.now()
//    val formatter = DateTimeFormatter.ofPattern("yyyy MM dd")
//    val text = date.format(formatter)
//    return LocalDate.parse(text, formatter).toString()
}