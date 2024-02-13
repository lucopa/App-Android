package com.example.miappcurso.data.datasource

import com.example.miappcurso.data.models.FilmsRepositoryInterfaceDao

class FilmsRepositoryDao : FilmsRepositoryInterfaceDao {
    override suspend fun getFilms(): List<List<String>> {
        return Film.peliculas
    }

    override suspend fun addFilm(film: List<String>) {
        Film.peliculas.add(film)
    }

    override suspend fun editFilm(film: List<String>) {

        val index = Film.peliculas.indexOfFirst { it[0] == film[0] }
        if (index != -1) {
            Film.peliculas[index] = film
        }
    }

    override suspend fun deleteFilm(film: List<String>) {
        Film.peliculas.removeIf { it[0] == film[0] }
    }
}
