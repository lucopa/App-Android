package com.example.miappcurso.domain.SharedPreferences

import android.app.Application

class UserTermsAplication : Application()  {

    companion object{
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}