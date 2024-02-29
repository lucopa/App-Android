package com.example.miappcurso.data.models


import com.example.miappcurso.data.service.FilmService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: FilmApiService
){

    suspend fun getUser(user : User) : User? {
        val userRequest = RequestLoginUser(user.email, user.passw)
        val result = apiService.getUser(userRequest)
        result
            .onSuccess {
                responseUser->
                return@onSuccess User(
                    responseUser.id,
                    responseUser.token,
                    responseUser.email,
                    responseUser.passw,
                    responseUser.disponible,
                    responseUser.image)

        }
            .onFailure {
                exception ->  println("Error en la excepcion ${exception.message}")
            }
        return null
    }
}