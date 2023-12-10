package com.example.miappcurso.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miappcurso.Films
import com.example.miappcurso.R

class FilmsAdapter(private val filmsList: List<Films>, private val onClickListener: (Films) -> Unit,
            private val onClickDelete:(Int) -> Unit
            ): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.card_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return filmsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filmsList[position]
        holder.funcion(item, onClickListener, onClickDelete)

    }
}