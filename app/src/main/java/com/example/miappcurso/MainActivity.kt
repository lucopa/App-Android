package com.example.miappcurso

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miappcurso.adapter.FilmsAdapter
import com.example.miappcurso.databinding.ActivityMainBinding
import com.example.miappcurso.databinding.AddFilmDialogBinding
import com.example.miappcurso.databinding.EditFilmDialogBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var filmsMutableList: MutableList<Films> = FilmsProvider.filmsList.toMutableList()
    private lateinit var adapter: FilmsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        binding.botonPelicula.setOnClickListener {
            showAddFilmDialog()
        }


    }


    private fun initRecyclerView() {
        adapter = FilmsAdapter(
            filmsList = filmsMutableList,
            onClickListener = { films -> onItemSelected(films) },
            onClickDelete = { position -> onDeleteItem(position) },
            onClickEdit = { position -> onEditItem(position) }
        )

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewFilms.layoutManager = layoutManager
        binding.recyclerViewFilms.adapter = adapter
    }



    private fun onDeleteItem(position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle("Confirmación")
            setMessage("¿Estás seguro de que deseas borrar esta película?")
            setPositiveButton("Sí") { dialog, which ->
                filmsMutableList.removeAt(position)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position, filmsMutableList.size)
            }
            setNegativeButton("No") { dialog, which ->

            }
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun onItemSelected(films: Films) {
        Toast.makeText(this, films.film, Toast.LENGTH_SHORT).show()
    }

    private fun onEditItem(position: Int) {
        val dialogView = EditFilmDialogBinding.inflate(layoutInflater)
        val selectedItem = filmsMutableList[position]

        dialogView.editTitulo.setText(selectedItem.film)
        dialogView.editGenero.setText(selectedItem.genero)

        val dialog = AlertDialog.Builder(this)
        dialog.setView(dialogView.root)
        dialog.setTitle("Editar película")

        dialog.setPositiveButton("Guardar") { _, _ ->
            val newTitle = dialogView.editTitulo.text.toString()
            val newGenre = dialogView.editGenero.text.toString()

            selectedItem.film = newTitle
            selectedItem.genero = newGenre


            adapter.notifyItemChanged(position)

            Toast.makeText(this, "Película editada: $newTitle", Toast.LENGTH_SHORT).show()
        }

        dialog.setNegativeButton("Cancelar") { _, _ ->
            Toast.makeText(this, "Edición cancelada", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
    }



    private fun showAddFilmDialog() {
        val dialogView = AddFilmDialogBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
        dialog.setView(dialogView.root)
        dialog.setTitle("Añadir Nueva Película")

        dialog.setPositiveButton("Guardar") { _, _ ->
            val newTitle = dialogView.editTitulo.text.toString()
            val newGenre = dialogView.editGenero.text.toString()
            val newPhotoUrl = dialogView.editPhotoUrl.text.toString()


            val newFilm = Films(newTitle, newGenre, newPhotoUrl)
            filmsMutableList.add(newFilm)
            adapter.notifyItemInserted(filmsMutableList.size - 1)

            Toast.makeText(this, "Nueva película añadida: $newTitle", Toast.LENGTH_SHORT).show()
        }

        dialog.setNegativeButton("Cancelar") { _, _ ->
            Toast.makeText(this, "Añadir película cancelado", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
    }


}