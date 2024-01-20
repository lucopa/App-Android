package com.example.miappcurso.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miappcurso.adapter.Films
import com.example.miappcurso.adapter.FilmsProvider
import com.example.miappcurso.adapter.FilmsAdapter
import com.example.miappcurso.databinding.AddFilmDialogBinding
import com.example.miappcurso.databinding.EditFilmDialogBinding
import com.example.miappcurso.databinding.FragmentRecyclerBinding

class FragmentRecycler : Fragment() {

    private lateinit var binding: FragmentRecyclerBinding
    private var filmsMutableList: MutableList<Films> = FilmsProvider.filmsList.toMutableList()
    private lateinit var adapter: FilmsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFilms.layoutManager = layoutManager
        binding.recyclerViewFilms.adapter = adapter
    }

    private fun onDeleteItem(position: Int) {
        val builder = AlertDialog.Builder(requireContext())
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
        Toast.makeText(requireContext(), films.film, Toast.LENGTH_SHORT).show()
    }

    private fun onEditItem(position: Int) {
        val dialogView = EditFilmDialogBinding.inflate(layoutInflater)
        val selectedItem = filmsMutableList[position]

        dialogView.editTitulo.setText(selectedItem.film)
        dialogView.editGenero.setText(selectedItem.genero)

        val dialog = AlertDialog.Builder(requireContext())
        dialog.setView(dialogView.root)
        dialog.setTitle("Editar película")

        dialog.setPositiveButton("Guardar") { _, _ ->
            val newTitle = dialogView.editTitulo.text.toString()
            val newGenre = dialogView.editGenero.text.toString()

            selectedItem.film = newTitle
            selectedItem.genero = newGenre


            adapter.notifyItemChanged(position)

            Toast.makeText(requireContext(), "Película editada: $newTitle", Toast.LENGTH_SHORT).show()
        }

        dialog.setNegativeButton("Cancelar") { _, _ ->
            Toast.makeText(requireContext(), "Edición cancelada", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
    }


    private fun showAddFilmDialog() {
        val dialogView = AddFilmDialogBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setView(dialogView.root)
        dialog.setTitle("Añadir Nueva Película")

        dialog.setPositiveButton("Guardar") { _, _ ->
            val newTitle = dialogView.editTitulo.text.toString()
            val newGenre = dialogView.editGenero.text.toString()
            val newPhotoUrl = dialogView.editPhotoUrl.text.toString()


            val newFilm = Films(newTitle, newGenre, newPhotoUrl)
            filmsMutableList.add(newFilm)
            adapter.notifyItemInserted(filmsMutableList.size - 1)

            Toast.makeText(requireContext(), "Nueva película añadida: $newTitle", Toast.LENGTH_SHORT).show()
        }

        dialog.setNegativeButton("Cancelar") { _, _ ->
            Toast.makeText(requireContext(), "Añadir película cancelado", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
    }

}
