package com.example.miappcurso.data.service

interface FilmServiceInterface {
    suspend fun getFilms(): List<List<String>>
    suspend fun addFilm(film: List<String>)
    suspend fun editFilm(film: List<String>)
    suspend fun deleteFilm(film: List<String>)
}