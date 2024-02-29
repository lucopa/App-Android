package com.example.miappcurso.domain.listener


interface OnUsuarioActionListener {
    fun registrarUsuario(email: String, password: String, nombre: String, imagen: String)
}
