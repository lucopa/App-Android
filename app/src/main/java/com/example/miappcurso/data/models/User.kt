package com.example.miappcurso.data.models

/*
Modelo para el usuario registrado
 */

data class User(var id: Int, var token: String, var email:String, var passw:String, val disponible: Int, var imagen: String){


    constructor(email: String, passw: String):
            this(0, "", email, passw, 0, "")

}
