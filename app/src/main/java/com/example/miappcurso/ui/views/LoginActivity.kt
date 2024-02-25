package com.example.miappcurso.ui.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miappcurso.R
import com.example.miappcurso.data.login.data.DatabaseUsuarioEntity
import com.example.miappcurso.data.login.data.UsuarioEntityDao
import com.example.miappcurso.databinding.ActivityLoginBinding
import com.example.miappcurso.domain.SharedPreferences.Preferencias
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var preferencias: Preferencias
    lateinit var dao : UsuarioEntityDao
    lateinit var database : DatabaseUsuarioEntity
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DatabaseUsuarioEntity.getDatabase(applicationContext);
        dao = database.usuarioEntityDao()
        preferencias = Preferencias(this)



        if(preferencias.logueado() == true){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val miBoton = findViewById<Button>(R.id.buttonLogin)
        val textoClicable = findViewById<Button>(R.id.buttonRegistrar)

        textoClicable.setOnClickListener {
            val intent = Intent(this, LoginActivity2::class.java)
            startActivity(intent)
        }


        miBoton.setOnClickListener {

            val editTextEmail = findViewById<EditText>(R.id.editTextCorreo)
            val valorEmail: String = editTextEmail.text.toString()

            val editTextPass = findViewById<EditText>(R.id.editTextTextPassword)
            val valorPass: String = editTextPass.text.toString()



            GlobalScope.launch(Dispatchers.IO) {
                val comprobarUser = dao.getUsuarioByEmailAndPassword(valorEmail, valorPass)
                if(comprobarUser!=null){
                    preferencias.guardarUsuario(valorEmail);
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@LoginActivity, "El email o contraseña es incorrecto", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }

    }



}