package com.example.miappcurso


import DetailFragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.miappcurso.databinding.ActivityMainBinding
import com.example.miappcurso.toolBar.MyToolBar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MyToolBar().show(this, "LISTA DE PELÍCULAS", true )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.option_one -> {
                val fragment = DetailFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit()
                Toast.makeText(this, "Recomendaciones de Películas", Toast.LENGTH_SHORT).show()

                return true
            }
            R.id.option_two -> {
                startActivity(Intent(this, LoginActivity::class.java))
                Toast.makeText(this, "Volviste al Login", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.option_three -> {
                startActivity(Intent(this, MainActivity::class.java))
                Toast.makeText(this, "Volviste a la Lista de Películas", Toast.LENGTH_SHORT).show()
                return true
            }


        }
        return super.onOptionsItemSelected(item)
    }


}