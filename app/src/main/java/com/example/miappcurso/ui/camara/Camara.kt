package com.example.miappcurso.ui.camara


import android.app.Activity
import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.widget.Toast
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

object Camara {
    fun almacenarFotoEnGaleria(acti: Activity, bitmap: Bitmap) {
        var fos: OutputStream? = null
        var f: File? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val values = ContentValues()
            val resolver: ContentResolver = acti.contentResolver
            val fileName = System.currentTimeMillis().toString() + "imagen_de_ejemplo"
            values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/AppPruebaCamara")
            values.put(MediaStore.Images.Media.IS_PENDING, 1)
            val collection: Uri = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            val imageUri: Uri? = resolver.insert(collection, values)
            try {
                fos = resolver.openOutputStream(imageUri!!)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
            values.clear()
            values.put(MediaStore.Images.Media.IS_PENDING, 0)
            resolver.update(imageUri!!, values, null, null)
        } else {
            val imageDir: String = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
            val fileName = System.currentTimeMillis().toString() + ".jpg;"
            f = File(imageDir, fileName)
            try {
                fos = FileOutputStream(f)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
        val salvado: Boolean = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos!!) // solo JPEG
        if (salvado) Toast.makeText(acti, "La imagen se ha guardado", Toast.LENGTH_SHORT).show()
        if (fos != null) {
            try {
                fos.flush()
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (f != null) MediaScannerConnection.scanFile(acti, arrayOf(f.toString()), null, null)
    }

    fun convert(base64Str: String): Bitmap {
        val decodedBytes: ByteArray = Base64.decode(base64Str.substring(base64Str.indexOf(",") + 1), Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    fun convert(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
    }
}
