package com.example.miappcurso.data.login.data

import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class UsuarioEntityApplication : Application() {
    companion object {
        lateinit var database: DatabaseUsuarioEntity
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            DatabaseUsuarioEntity::class.java, "usuarios"
        ).build()
    }
}