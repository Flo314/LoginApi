package com.example.loginapi.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.loginapi.data.network.MyApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository {

    fun userLogin(email: String, password:String): LiveData<String> {

        val loginResponse = MutableLiveData<String>()
        /* problème car on crée une instance de MyApi à l'intérieur de UserRepository
        et cela rend la classe UserRepository dépendante de MyApi c'est une mauvaise pratique
        il vaut mieux l'injecter mais pour l'instant je fais comme çà*/
        MyApi().userLogin(email, password)
            .enqueue(object: Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }

                override fun onResponse(call: Call<ResponseBody>,response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        loginResponse.value = response.body()?.string()
                    } else {
                        loginResponse.value = response.errorBody()?.string()
                    }
                }

            })
        return loginResponse
    }
}