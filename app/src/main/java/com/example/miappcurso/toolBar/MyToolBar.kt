package com.example.miappcurso.toolBar

import androidx.appcompat.app.AppCompatActivity
import com.example.miappcurso.R

class MyToolBar {
    private lateinit var toolBar: MyToolBar
    fun show(activities: AppCompatActivity, title: String, upButton:Boolean){
        activities.setSupportActionBar(activities.findViewById(R.id.toolbar))
        activities.supportActionBar?.title = title
        activities.supportActionBar?.setDisplayHomeAsUpEnabled(upButton)
    }
}