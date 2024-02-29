package com.example.miappcurso.domain.usercase


import com.example.miappcurso.data.models.User
import com.example.miappcurso.data.models.UserRepository
import javax.inject.Inject

class LoginUserCase  @Inject constructor(
    private val userRepository: UserRepository,
    private var posibleUser : User
){
    fun setUser(_posibleUser : User){
        posibleUser = _posibleUser
    }

    suspend operator fun invoke(): User ?{
        return (userRepository.getUser(posibleUser))
    }
}