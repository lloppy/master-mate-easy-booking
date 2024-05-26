package com.example.skills.data.api


import com.example.skills.data.roles.User
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @POST("api/auth/register")
    suspend fun register(@Body authRequest: AuthRequest): Response<AuthResponse>

    @POST("api/auth/authenticate")
    suspend fun authenticate(@Body authRequest: AuthRequest): Response<AuthResponse>

    @POST("/api/account/activate")
    suspend fun activate(@Header("Authorization") token: String, @Body activationRequest: ActivationRequest): Response<ActivationResponse>

    @GET("api/users/me")
    suspend fun getUserByToken(@Header("Authorization") token: String): Response<User>

    @Multipart
    @POST("api/users/me/edit/profilePicture")
    suspend fun uploadProfilePicture(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): Response<String>
    @POST("api/users/me/get/profilePicture")
    suspend fun getProfilePicture(
        @Header("Authorization") token: String
    ): Response<String>

    @POST("api/masters/me/edit")
    suspend fun editMasterProfile(@Body editMasterRequest: EditMasterRequest): Response<EditMasterResponse>

    @POST("api/clients/me/edit")
    suspend fun editClientProfile(@Body editClientRequest: EditClientRequest): Response<EditClientResponse>

}