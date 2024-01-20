package com.example.miappcurso.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.miappcurso.databinding.CardLayoutBinding

class ViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val binding = CardLayoutBinding.bind(view)


    fun funcion(filmsModel: Films, onClickListener: (Films) -> Unit, onClickDelete: (Int) -> Unit){
        binding.tituloPelicula.text = filmsModel.film
        binding.tituloGenero.text = filmsModel.genero
        Glide.with(binding.image.context).load(filmsModel.photo).into(binding.image)
        binding.image.setOnClickListener{ Toast.makeText(binding.image.context, filmsModel.film, Toast.LENGTH_SHORT).show()}
        binding.botonBorrar.setOnClickListener{onClickDelete(bindingAdapterPosition)}

    }
}