package com.example.skills.repository

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("api/auth/register")
    suspend fun register(@Body authRequest: AuthRequest): Response<AuthResponse>

    @POST("api/auth/authenticate")
    suspend fun authenticate(@Body authRequest: AuthRequest): Response<AuthResponse>

    @POST("api/account/activate")
    suspend fun activate(@Body activationRequest: ActivationRequest): Response<ActivationResponse>

    @GET("/hello")
    suspend fun checkUser(): Response<String>
}