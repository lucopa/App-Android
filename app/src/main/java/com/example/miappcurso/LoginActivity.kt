package com.example.miappcurso

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miappcurso.SharedPreferences.UserTermsAplication.Companion.prefs
import com.example.miappcurso.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        checkUserValues()
    }

    fun checkUserValues(){
        if(prefs.getName().isNotEmpty()){
            goToDetail()
        }

    }

    private fun initUI() {
        binding.buttonLogin.setOnClickListener {

            accessDetail()
        }
    }

    private fun accessDetail() {
        if(binding.editTextText.text.toString().isNotEmpty()){
            prefs.saveName(binding.editTextText.text.toString())
            prefs.saveTERMS(binding.checkBoxVip.isChecked)
            goToDetail()
        }
        else{

        }
    }

    fun goToDetail(){
        startActivity(Intent(this,MainActivity::class.java))
    }


}
