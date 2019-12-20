package com.example.loginapi.data.repositories

import com.example.loginapi.data.network.MyApi
import com.example.loginapi.data.network.responses.AuthResponse
import com.example.loginapi.data.network.responses.SafeApiRequest
import retrofit2.Response


class UserRepository : SafeApiRequest(){

    suspend fun userLogin(email: String, password:String): AuthResponse {

       return apiRequest { MyApi().userLogin(email, password) }
    }
}