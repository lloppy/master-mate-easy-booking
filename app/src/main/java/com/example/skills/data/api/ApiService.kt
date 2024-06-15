package com.example.skills.data.api


import com.example.skills.data.entity.Category
import com.example.skills.data.entity.CategoryRequest
import com.example.skills.data.entity.EditServiceRequest
import com.example.skills.data.entity.RecordItem
import com.example.skills.data.entity.Schedule
import com.example.skills.data.entity.Service
import com.example.skills.data.entity.ServiceRequest
import com.example.skills.data.roles.User
import com.example.skills.data.roles.UserTokenResponse
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @POST("api/auth/register")
    suspend fun register(@Body authRequest: AuthRequest): Response<AuthResponse>

    @POST("api/auth/authenticate")
    suspend fun authenticate(@Body authRequest: LogInRequest): Response<AuthResponse>

    @POST("/api/account/activate")
    suspend fun activate(
        @Header("Authorization") token: String,
        @Body activationRequest: ActivationRequest
    ): Response<ActivationResponse>

    @GET("api/users/me")
    suspend fun getUserByToken(
        @Header("Authorization") token: String
    ): Response<User>

    @GET("api/users/{id}")
    suspend fun getUserById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<UserTokenResponse>

    @Multipart
    @POST("api/users/me/edit/profilePicture")
    suspend fun uploadProfilePicture(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): Response<ResponseBody>

    @Multipart
    @POST("/api/masters/me/upload_image")
    suspend fun uploadMastersPicture(
        @Header("Authorization") token: String,
        @Part picture: MultipartBody.Part
    ): Response<ResponseBody>

    @GET("api/users/me/get/profilePicture")
    suspend fun getProfilePicture(@Header("Authorization") token: String): Response<ResponseBody>

    @GET("/api/masters/me/additional_images")
    suspend fun getMastersWorksId(@Header("Authorization") token: String): Response<List<Int>>

    @GET("/api/images/{id}")
    suspend fun getMastersWorkById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<ResponseBody>

    @POST("api/masters/me/edit")
    suspend fun editMasterProfile(
        @Header("Authorization") token: String,
        @Body editMasterRequest: EditMasterRequest
    ): Response<EditMasterResponse>

    @POST("api/clients/me/edit")
    suspend fun editClientProfile(
        @Header("Authorization") token: String,
        @Body editClientRequest: EditClientRequest
    ): Response<EditClientResponse>


    // Service controller
    @POST("/api/masters/me/categories/{category_id}/services")
    suspend fun addService(
        @Header("Authorization") token: String,
        @Path("category_id") categoryId: Int,
        @Body service: ServiceRequest
    ): Response<String>

    @PUT("api/masters/me/services/{id}")
    suspend fun editService(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body service: EditServiceRequest
    ): Response<ResponseBody>

    @DELETE("/api/masters/me/services/{id}")
    suspend fun removeService(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<ResponseBody>

    @GET("/api/masters/{id}/services")
    suspend fun getMasterServicesByMasterId(@Path("id") id: Int): Response<List<Service>>

    @GET("/api/masters/me/services")
    suspend fun getMasterServicesByToken(@Header("Authorization") token: String): Response<List<Service>> // было MasterServices

    @GET("/api/masters/categories/{id}/services")
    suspend fun getCategoryServices(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<List<Service>>

    // Category controller
    @PUT("/api/masters/me/categories/{id}")
    suspend fun editCategory(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body category: CategoryRequest
    ): Response<ResponseBody>

    @DELETE("/api/masters/me/categories/{id}")
    suspend fun deleteCategory(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<ResponseBody>

    @GET("/api/masters/me/categories")
    suspend fun getMasterCategoriesByToken(@Header("Authorization") token: String): Response<List<Category>>

    @POST("/api/masters/me/categories")
    suspend fun addCategory(
        @Header("Authorization") token: String,
        @Body categoryRequest: CategoryRequest
    ): Response<ResponseBody>

    @GET("/api/masters/{id}/categories")
    suspend fun getMasterCategoriesById(@Path("id") id: Int): Response<List<Category>>


    // ScheduleService
    @GET("/api/masters/me/schedules")
    suspend fun getScheduleByToken(@Header("Authorization") token: String): Response<List<Schedule>>

    @PUT("/api/masters/me/schedules")
    suspend fun changeSchedule(
        @Header("Authorization") token: String,
        @Body scheduleCreateRequest: ScheduleCreateRequest
    ): Response<String>

    @POST("/api/masters/me/schedules")
    suspend fun createSchedule(
        @Header("Authorization") token: String,
        @Body scheduleCreateRequest: ScheduleCreateRequest
    ): Response<ResponseBody>

    @HTTP(method = "DELETE", path = "/api/masters/me/schedules", hasBody = true)
    suspend fun deleteSchedule(
        @Header("Authorization") token: String,
        @Body dates: List<String>
    ): Response<ResponseBody>

    @GET("/api/masters/{id}/schedules")
    suspend fun getScheduleByMasterId(@Path("id") id: Long): Response<List<Schedule>>

    @GET("/api/masters/{id}")
    suspend fun getMasterById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<MasterForClientResponse>


    // image-controller
    @GET("api/images/{id}")
    suspend fun getImageById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<ResponseBody>

    @GET("/api/masters/{id}/schedules")
    suspend fun getSchedulesById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<List<Schedule>>

    @PUT("api/clients/me/masters/{id}")
    suspend fun addAddedMaster(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<ResponseBody>

    @GET("api/masters/{masterId}/services/{serviceId}/timeslots")
    suspend fun getFreeTimeSlots(
        @Header("Authorization") token: String,
        @Path("serviceId") serviceId: Int,
        @Path("masterId") masterId: Int,
        @Query("date") date: String
    ): Response<List<String>>

    @POST("api/masters/{id}/records")
    suspend fun createRecord(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body bookingData: BookingRequest
    ): Response<ResponseBody>

    @GET("api/masters/me/records")
    suspend fun getRecords(
        @Header("Authorization") token: String,
    ): Response<List<RecordItem>>

    @GET("api/masters/records/my")
    suspend fun getClientRecords(
        @Header("Authorization") token: String,
    ): Response<List<RecordItem>>

    @DELETE("api/masters/me/records")
    suspend fun deleteAllRecords(
        @Header("Authorization") token: String,
        @Query("date") date: String
    ): Response<ResponseBody>

    @DELETE("api/masters/records/{id}")
    suspend fun deleteRecord(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<ResponseBody>
}