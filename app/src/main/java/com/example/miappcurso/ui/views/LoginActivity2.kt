package com.example.miappcurso.ui.views


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.miappcurso.R
import com.example.miappcurso.data.login.data.DatabaseUsuarioEntity
import com.example.miappcurso.data.login.data.UsuarioEntity
import com.example.miappcurso.data.login.data.UsuarioEntityDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity2 : AppCompatActivity() {
    lateinit var userDao : UsuarioEntityDao
    lateinit var userDatabase : DatabaseUsuarioEntity
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        val ButtonRegister = findViewById<Button>(R.id.buttonRegistrar)
        val ButtonBack = findViewById<ImageButton>(R.id.buttonAtras)
        userDatabase = DatabaseUsuarioEntity.getDatabase(applicationContext);
        userDao = userDatabase.usuarioEntityDao()

        ButtonBack.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        ButtonRegister.setOnClickListener {
            val editEmailRegistro = findViewById<EditText>(R.id.emailRegistro)
            val newEmail = editEmailRegistro.text.toString()

            val passwordLogin = findViewById<EditText>(R.id.contrasenaRegistro)
            val newPassword = passwordLogin.text.toString()

            val nuevoUsuario = UsuarioEntity (email = newEmail, contrasena = newPassword)

            GlobalScope.launch {
                val existingUser = userDao.getUsuarioByEmail(newEmail)
                if (existingUser == null) {
                    userDao.insertarUsuario(nuevoUsuario)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@LoginActivity2, "Te has registrado correctamente", Toast.LENGTH_LONG).show()
                        delay(1000)
                        finish()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@LoginActivity2, "Este email ya esta registrado", Toast.LENGTH_LONG).show()
                    }
                }
            }


        }

    }
}