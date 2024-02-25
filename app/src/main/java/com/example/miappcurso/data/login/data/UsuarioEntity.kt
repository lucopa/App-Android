package com.example.miappcurso.data.login.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true) val id: Int=1,
    @ColumnInfo(name="email") val email:String,
    @ColumnInfo(name="contrasena") val contrasena:String
)
