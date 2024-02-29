package com.example.miappcurso.ui.camara



import android.Manifest
import android.app.Activity
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import com.example.miappcurso.R


object PermisosCamara {
    fun compruebaPermisosCamara(activity: Activity, RESPUESTA_PERMISO_CAMARA: Int, shared: SharedPreferences): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                // Si los permisos fueron ya concedidos por el usuario, simplemente usamos la cámara
                val editor: SharedPreferences.Editor = shared.edit()
                editor.putBoolean(activity.getString(R.string.preferencias_permiso_camara), true)
                editor.commit()
                return true
            } else {
                /*
                Solicitamos permisos al usuario y esperamos una respuesta.
                - Necesita el Activity, un array con los permisos a solicitar. En nuestro caso, lo hacemos uno a uno.
                y el código de respuesta para que al comprobarlo, sepamos de dónde venimos.
                 */
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.CAMERA),
                    RESPUESTA_PERMISO_CAMARA
                )
                return false
            }
        } else {
            // no tenemos que pedir permisos, porque nuestro sdk es inferior a la 23.
            return true
        }
    }
}
