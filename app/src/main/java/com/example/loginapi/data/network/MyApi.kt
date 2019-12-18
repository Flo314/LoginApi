package com.example.loginapi.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

//const val BASE_URL = "http://localhost:3000/api/users/"
// pour l'émulateur
const val BASE_URL = "http://10.0.2.2:3000/api/users/"

/**
 * Interface qui couvre les différentes urls a appeler pour recevoir les données
 * Définit tous les appels à l'API (GET,POST...)
 */
interface MyApi {

    @FormUrlEncoded
    @POST("login")
    fun userLogin(
        @Field("email")email: String,
        @Field("password")password: String
    ): Call<ResponseBody>

    // singleton
    companion object {
        // permet de faire des appel -> MyApi() équivaut à MyApi.invoke()
        operator fun invoke() : MyApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}