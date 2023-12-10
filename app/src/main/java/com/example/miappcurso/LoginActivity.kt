package com.example.miappcurso

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miappcurso.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonLogin.setOnClickListener {
            val enteredUsername = binding.editTextText.text.toString()
            val enteredPassword = binding.editTextTextPassword.text.toString()

            val validUsername = resources.getString(R.string.valid_username)
            val validPassword = resources.getString(R.string.valid_password)

            if (enteredUsername == validUsername && enteredPassword == validPassword) {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this@LoginActivity, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

