package com.example.skills.data.api


import com.example.skills.data.entity.Category
import com.example.skills.data.entity.CategoryRequest
import com.example.skills.data.entity.Service
import com.example.skills.data.entity.ServiceRequest
import com.example.skills.data.roles.User
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import okhttp3.ResponseBody


interface ApiService {

    @POST("api/auth/register")
    suspend fun register(@Body authRequest: AuthRequest): Response<AuthResponse>

    @POST("api/auth/authenticate")
    suspend fun authenticate(@Body authRequest: LogInRequest): Response<AuthResponse>

    @POST("/api/account/activate")
    suspend fun activate(@Header("Authorization") token: String, @Body activationRequest: ActivationRequest): Response<ActivationResponse>

    @GET("api/users/me")
    suspend fun getUserByToken(@Header("Authorization") token: String): Response<User>

    @Multipart
    @POST("api/users/me/edit/profilePicture")
    suspend fun uploadProfilePicture(@Header("Authorization") token: String, @Part file: MultipartBody.Part): Response<String>

    @POST("api/users/me/get/profilePicture")
    suspend fun getProfilePicture(@Header("Authorization") token: String): Response<String>

    @POST("api/masters/me/edit")
    suspend fun editMasterProfile(@Header("Authorization") token: String, @Body editMasterRequest: EditMasterRequest): Response<EditMasterResponse>

    @POST("api/clients/me/edit")
    suspend fun editClientProfile(@Header("Authorization") token: String, @Body editClientRequest: EditClientRequest): Response<EditClientResponse>


    // Service controller

    @PUT("/api/masters/me/services/{id}")
    suspend fun changeService(@Header("Authorization") token: String, @Path("id") id: Int, @Body service: Service): Response<String>

    @DELETE("/api/masters/me/services/{id}")
    suspend fun removeService(@Header("Authorization") token: String, @Path("id") id: Int): Response<String>

    @Multipart
    @POST("/api/masters/me/services/{service_id}/edit/image")
    suspend fun addServiceImage(@Header("Authorization") token: String, @Path("service_id") serviceId: Int, @Part file: MultipartBody.Part): Response<String>

    @POST("/api/masters/me/categories/{category_id}/services")
    suspend fun addService(@Header("Authorization") token: String, @Path("category_id") categoryId: Int, @Body service: ServiceRequest): Response<String>

    @GET("/api/masters/{id}/services")
    suspend fun getMasterServicesByMasterId(@Path("id") id: Int): Response<List<Service>>

    @GET("/api/masters/services/{id}/image")
    suspend fun getServiceImage(@Path("id") id: Int): Response<String>

    @GET("/api/masters/me/services")
    suspend fun getMasterServicesByToken(@Header("Authorization") token: String): Response<List<Service>> // было MasterServices

    @GET("/api/masters/categories/{id}/services")
    suspend fun getCategoryServices(@Path("id") id: Int): Response<List<Service>>

    // Category controller
    @PUT("/api/masters/me/categories/{id}")
    suspend fun changeCategory(@Header("Authorization") token: String, @Path("id") id: Int, @Body category: Category): Response<String>

    @DELETE("/api/masters/me/categories/{id}")
    suspend fun removeCategory(@Header("Authorization") token: String, @Path("id") id: Int): Response<String>

    @GET("/api/masters/me/categories")
    suspend fun getMasterCategoriesByToken(@Header("Authorization") token: String): Response<List<Category>>

    @POST("/api/masters/me/categories")
    suspend fun addCategory(@Header("Authorization") token: String, @Body categoryRequest: CategoryRequest): Response<ResponseBody>

    @GET("/api/masters/{id}/categories")
    suspend fun getMasterCategoriesById(@Path("id") id: Int): Response<List<Category>>

}