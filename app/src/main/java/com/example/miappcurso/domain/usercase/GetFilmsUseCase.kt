package com.example.miappcurso.domain.usercase

import com.example.miappcurso.data.datasource.FilmsRepositoryDao
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(private val filmsRepository : FilmsRepositoryDao) {
    suspend operator fun invoke(): List<List<String>> {
        return filmsRepository.getFilms()
    }
}