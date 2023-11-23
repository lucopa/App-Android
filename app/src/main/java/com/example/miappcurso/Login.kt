package com.example.miappcurso

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    private val MYUSER = "usuario"
    private val MYPASS = "contraseña"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val buttonValidar: Button = findViewById(R.id.buttonRegistro)
        val editTextUser: EditText = findViewById(R.id.editTextText)
        val editTextPassword: EditText = findViewById(R.id.editTextTextPassword)

        buttonValidar.setOnClickListener {
            val inputUser = editTextUser.text.toString()
            val inputPass = editTextPassword.text.toString()

            if (inputUser == MYUSER && inputPass == MYPASS) {
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("usuario", inputUser)
                    putExtra("contraseña", inputPass)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}