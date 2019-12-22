package com.example.loginapi.data.repositories

import com.example.loginapi.data.db.AppDatabase
import com.example.loginapi.data.db.entities.User
import com.example.loginapi.data.network.MyApi
import com.example.loginapi.data.network.responses.AuthResponse
import com.example.loginapi.data.network.responses.SafeApiRequest
import retrofit2.Response


class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest(){

    suspend fun userLogin(email: String, password:String): AuthResponse {

       return apiRequest { api.userLogin(email, password) }
    }

    // enregistrer le user dans la db locale
    // suspend car operation en db asynchrone
    suspend fun saveUserDb(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}