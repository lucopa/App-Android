package com.example.miappcurso

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador : RecyclerView.Adapter<Adaptador.ViewHolder>(){

    val titles = arrayOf("Get Out",
        "Pretty Woman",
        "Pitch Perfect")

    val titles2 = arrayOf("Terror",
        "Amor y Drama",
        "Musical y Comedia")

    val images = arrayOf(R.drawable.getout,
        R.drawable.prettywoman,
        R.drawable.pitchperfect)



    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout,viewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.element_tittle.text =titles[i]
        viewHolder.element_tittle2.text=titles2[i]
        viewHolder.element_image.setImageResource(images[i])
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var element_image: ImageView
        var element_tittle: TextView
        var element_tittle2: TextView

        init {
            element_image = itemView.findViewById(R.id.image)
            element_tittle = itemView.findViewById(R.id.titulo)
            element_tittle2 = itemView.findViewById(R.id.titulo2)


        }

    }
}