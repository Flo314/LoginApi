package com.example.loginapi.data.repositories

import com.example.loginapi.data.network.MyApi
import com.example.loginapi.data.network.responses.AuthResponse
import retrofit2.Response


class UserRepository {

    suspend fun userLogin(email: String, password:String): Response<AuthResponse> {

       return MyApi().userLogin(email, password)
    }
}