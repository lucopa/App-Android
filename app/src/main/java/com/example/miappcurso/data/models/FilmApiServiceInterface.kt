package com.example.miappcurso.data.models



import com.example.miappcurso.network.response.ResponseLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FilmApiServiceInterface {

    @POST("auth")
    suspend fun login(@Body loginUser : RequestLoginUser): Response<ResponseLogin>

    enum class RequestLoginUser {

    }


}


