package com.example.miappcurso

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miappcurso.adapter.FilmsAdapter
import com.example.miappcurso.databinding.ActivityMainBinding

// MainActivity.kt
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var filmsMutableList: MutableList<Films> = FilmsProvider.filmsList.toMutableList()
    private lateinit var adapter: FilmsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()


    }

    private fun initRecyclerView() {

        adapter = FilmsAdapter(filmsList = filmsMutableList,
            onClickListener = { films -> onItemSelected(films) },
            onClickDelete = {position -> onDeleteItem(position)}
            )

        binding.recyclerViewFilms.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewFilms.adapter = adapter
    }

    private fun onDeleteItem(position:Int){
        filmsMutableList.removeAt(position)
        adapter.notifyItemRemoved(position)

    }
    private fun onItemSelected(films: Films) {
        Toast.makeText(this, films.film, Toast.LENGTH_SHORT).show()
    }
}