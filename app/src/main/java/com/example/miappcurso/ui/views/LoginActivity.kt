package com.example.miappcurso.ui.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miappcurso.domain.SharedPreferences.UserTermsAplication.Companion.prefs
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
        if (binding.editTextText.text.toString().isNotEmpty() && binding.editTextCorreo.text.toString().isNotEmpty()) {
            if (binding.checkBoxVip.isChecked) {
                prefs.saveName(binding.editTextText.text.toString())
                prefs.saveCorreo(binding.editTextCorreo.text.toString()) // Guardar correo electrónico
                prefs.saveTERMS(binding.checkBoxVip.isChecked)
                goToDetail()
            } else {
                Toast.makeText(
                    this,
                    "Debes aceptar los términos y condiciones para iniciar sesión",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                this,
                "Por favor ingresa un nombre de usuario y correo electrónico",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    fun goToDetail(){
        startActivity(Intent(this, MainActivity::class.java))
    }




}
