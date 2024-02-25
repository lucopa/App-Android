package com.example.miappcurso.domain.SharedPreferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

    fun saveUser(email: String) {
        val editor = sharedPreferences.edit()
        editor.putString("correo", email)
        editor.putBoolean("logIn", true)
        editor.apply()
    }

    fun getUser(): String? {
        return sharedPreferences.getString("correo", null)
    }

    fun logIn(): Boolean {
        return sharedPreferences.getBoolean("logIn", false)
    }

    fun deletePreferences() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
