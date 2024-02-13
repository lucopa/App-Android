package com.example.miappcurso.domain.usercase

import com.example.miappcurso.data.datasource.FilmsRepositoryDao

class GetFilmsUseCase {
    private val filmsRepository = FilmsRepositoryDao()

    suspend operator fun invoke(): List<List<String>> {
        return filmsRepository.getFilms()
    }
}