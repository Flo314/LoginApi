package com.example.loginapi.data.network

import com.example.loginapi.data.network.responses.AuthResponse
import com.example.loginapi.data.network.responses.CommentsResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

//const val BASE_URL = "http://localhost:3000/api/v1/users/"
// pour l'émulateur
const val BASE_URL = "http://10.0.2.2:3000/api/v1/"

/**
 * Interface qui couvre les différentes urls a appeler pour recevoir les données
 * Définit tous les appels à l'API (GET,POST...)
 */
interface MyApi {

    @FormUrlEncoded
    @POST("users/login")
    // suspend est au centre des coroutines et veut dire que c'est une fonction qui peut être suspendue et reprise
    // asynchrone
    suspend fun userLogin(
        @Field("email")email: String,
        @Field("password")password: String
    ): Response<AuthResponse>

    @FormUrlEncoded
    @POST("users/signup")
    suspend fun userSignup(
        @Field("firstname")firstname: String,
        @Field("lastname")lastname: String,
        @Field("email")email: String,
        @Field("password")password: String
    ) : Response<AuthResponse>

    @GET("comments")
    suspend fun getComments(): Response<CommentsResponse>


    // singleton
    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : MyApi {

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(networkConnectionInterceptor)
            .build()

        // permet de faire des appel -> MyApi() équivaut à MyApi.invoke()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}