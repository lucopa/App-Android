package com.example.miappcurso


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
        if (item.itemId == R.id.option_one)
            Toast.makeText(this, "Detalle Películas", Toast.LENGTH_SHORT).show()
        if (item.itemId == R.id.option_two)
            startActivity(Intent(this, LoginActivity::class.java))
        Toast.makeText(this, "Volviste al Login", Toast.LENGTH_SHORT).show()


        return super.onOptionsItemSelected(item)
    }

}