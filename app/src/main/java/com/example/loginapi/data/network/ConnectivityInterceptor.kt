package com.example.loginapi.data.network

import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().build()

        return chain.proceed(request)
    }
}