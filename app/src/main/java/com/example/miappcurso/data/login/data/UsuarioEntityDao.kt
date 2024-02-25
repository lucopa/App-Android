package com.example.miappcurso.data.login.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsuarioEntityDao {

    @Query("SELECT * FROM usuarios")
    fun getAll(): List<UsuarioEntity>

    @Query("SELECT * FROM usuarios WHERE email = :email AND contrasena = :contrasena")
    fun getUsuarioByEmailAndPassword(email: String, contrasena: String): UsuarioEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarUsuario(usuario: UsuarioEntity)

    @Query("SELECT * FROM usuarios WHERE email = :email ")
    fun getUsuarioByEmail(email: String): UsuarioEntity

    @Query("DELETE FROM usuarios")
    fun borrarUsuarioPorId()

}