package com.example.miappcurso.network.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class RequestLoginUser(


    @SerializedName("email")
    @Expose
    val email : String,


    @SerializedName("password")
    @Expose
    val passw : String
) {

}