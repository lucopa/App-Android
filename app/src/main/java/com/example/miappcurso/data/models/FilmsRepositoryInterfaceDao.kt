package com.example.miappcurso.data.models

import com.example.miappcurso.data.datasource.Film

interface FilmsRepositoryInterfaceDao {
    suspend fun getFilms(): List<List<String>>
    suspend fun addFilm(film: List<String>)
    suspend fun editFilm(film: List<String>)
    suspend fun deleteFilm(film: List<String>)
}