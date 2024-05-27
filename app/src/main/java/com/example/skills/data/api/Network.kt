package com.example.skills.data.api

import android.util.Log
import com.example.skills.data.viewmodel.MY_LOG
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Network {

    private val defaultClient = OkHttpClient.Builder()
        .readTimeout(40, TimeUnit.SECONDS)
        .connectTimeout(40, TimeUnit.SECONDS)
        .build()

    private var customClient: OkHttpClient? = null

    private fun provideClient() = customClient ?: defaultClient

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://localhost:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideClient())
            .build()
            .create(ApiService::class.java)
    }

    fun updateToken(token: String?) {
        if (token != null) {
            val authTokenInterceptor = Interceptor { chain ->
                val request =
                    chain.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
                Log.d(MY_LOG, "new token is $token")
                chain.proceed(request)
            }

            customClient = defaultClient
                .newBuilder()
                .addNetworkInterceptor(authTokenInterceptor)
                .build()
        } else {
            customClient = null
        }
    }
}