package com.example.miappcurso.ui.views


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
    lateinit var dao : UsuarioEntityDao
    lateinit var database : DatabaseUsuarioEntity
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        val BotonRegistrarse = findViewById<Button>(R.id.buttonRegistrar)
        val BotonVolver = findViewById<Button>(R.id.buttonAtras)
        database = DatabaseUsuarioEntity.getDatabase(applicationContext);
        dao = database.usuarioEntityDao()

        BotonVolver.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        BotonRegistrarse.setOnClickListener {
            val editNuevoEmail = findViewById<EditText>(R.id.emailRegistro)
            val nuevoEmail = editNuevoEmail.text.toString()

            val editsetNuevaContraseña = findViewById<EditText>(R.id.contrasenaRegistro)
            val nuevaContraseña = editsetNuevaContraseña.text.toString()

            val nuevoUsuario = UsuarioEntity (email = nuevoEmail, contrasena = nuevaContraseña)

            GlobalScope.launch {
                val existingUser = dao.getUsuarioByEmail(nuevoEmail)
                if (existingUser == null) {
                    dao.insertarUsuario(nuevoUsuario)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@LoginActivity2, "Te has registrado correctamente", Toast.LENGTH_LONG).show()
                        delay(2000)
                        finish() // Cierra la actividad actual
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