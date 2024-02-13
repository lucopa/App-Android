package com.example.miappcurso.data.service

import com.example.miappcurso.data.datasource.FilmsRepositoryDao
import com.example.miappcurso.data.models.FilmsRepositoryInterfaceDao

class FilmService : FilmServiceInterface {
    private val repository: FilmsRepositoryInterfaceDao = FilmsRepositoryDao()

    override suspend fun getFilms(): List<List<String>> {
        return repository.getFilms()
    }

    override suspend fun addFilm(film: List<String>) {
        repository.addFilm(film)
    }

    override suspend fun editFilm(film: List<String>) {
        repository.editFilm(film)
    }

    override suspend fun deleteFilm(film: List<String>) {
        repository.deleteFilm(film)
    }
}