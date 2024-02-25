package com.example.miappcurso.domain.SharedPreferences

import android.content.Context
import android.content.SharedPreferences

class Preferencias(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("PrefenciasCompartidas", Context.MODE_PRIVATE)

    fun guardarUsuario(email: String) {
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.putBoolean("logueado", true)
        editor.apply()
    }

    fun obtenerUsuario(): String? {
        return sharedPreferences.getString("email", null)
    }

    fun logueado(): Boolean {
        return sharedPreferences.getBoolean("logueado", false)
    }

    fun borrarPreferencias() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
