package com.example.skills.data.viewmodel

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.toLowerCase
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skills.MainActivity
import com.example.skills.data.api.ActivationRequest
import com.example.skills.data.api.AuthRequest
import com.example.skills.data.api.BookingRequest
import com.example.skills.data.api.CategoryResponse
import com.example.skills.data.api.EditMasterRequest
import com.example.skills.data.api.LogInRequest
import com.example.skills.data.api.MasterForClient
import com.example.skills.data.api.Network
import com.example.skills.data.api.Network.apiService
import com.example.skills.data.api.ScheduleCreateRequest
import com.example.skills.data.entity.Category
import com.example.skills.data.entity.CategoryRequest
import com.example.skills.data.entity.Duration
import com.example.skills.data.entity.EditServiceRequest
import com.example.skills.data.entity.RecordItem
import com.example.skills.data.entity.Schedule
import com.example.skills.data.entity.Service
import com.example.skills.data.entity.ServiceRequest
import com.example.skills.data.roles.Role
import com.example.skills.data.roles.User
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.SocketTimeoutException
import kotlin.random.Random
import kotlin.system.exitProcess

const val MY_LOG = "MY_LOG"

class MainViewModel(private val context: Context) : ViewModel() {
    var currentUser: User? by mutableStateOf(null)
        private set

    private var _userToken: String? = null
        private set
    private var _userRole: String? = null
        private set
    val userRole: String?
        get() = _userRole

    val servicesLiveDataMaster = MutableLiveData<List<Service>?>()
    val categoriesLiveDataMaster = MutableLiveData<List<Category>?>()

    private val _servicesLiveDataClient = MutableLiveData<List<Service>>()
    val servicesLiveDataClient: LiveData<List<Service>> = _servicesLiveDataClient

    private val _schedulesLiveData = MutableLiveData<List<Schedule>>()
    val schedulesLiveData: LiveData<List<Schedule>> get() = _schedulesLiveData

    private val _freeSlotsLiveData = MutableLiveData<List<String>>()
    val freeSlotsLiveData: LiveData<List<String>> get() = _freeSlotsLiveData

    private val _recordsLiveData = MutableLiveData<List<RecordItem>>()
    val recordsLiveData: LiveData<List<RecordItem>> get() = _recordsLiveData

    val userIsAuthenticated = mutableStateOf(false)
    private val preferences: SharedPreferences =
        context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)

    private val _isLoading = MutableStateFlow(false)
    var isLoading: StateFlow<Boolean> = _isLoading

    private val _mastersForClient = MutableStateFlow<List<MasterForClient>>(emptyList())
    val mastersForClient: StateFlow<List<MasterForClient>> = _mastersForClient

    init {
        Log.d(MY_LOG, "Initializing ViewModel, reading user credentials")
        _userToken = preferences.getString("token", null)
        _userRole = preferences.getString("role", null)

        if (_userRole != null) {
            Log.i(MY_LOG, "👋 Init session. Role is $_userRole, token is $_userToken")

            _userToken?.let {
                Network.updateToken(it)
                userIsAuthenticated.value = true
                loadCurrentUser(it, context, _userRole!!)
            }
        } else {
            Log.d(MY_LOG, "Gg, it`s old deprecated session 💩💩")
        }
    }

    fun registerUser(password: String, authRequest: AuthRequest, onResponse: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.emit(true)

            try {
                val response = apiService.register(authRequest)
                Log.d(MY_LOG, "Try receive token: ${response.body()?.token}")

                if (response.isSuccessful && response.body()?.token != null) {
                    Log.i(MY_LOG, "Token received isSuccessful: ${response.body()?.token}")

                    _userToken = response.body()!!.token
                    saveTokenToPreferences(_userToken!!)
                    Network.updateToken(_userToken)
                    userIsAuthenticated.value = true

                    // Client
                    if (authRequest.birthDate != null) {
                        currentUser = User(
                            token = _userToken!!,
                            email = authRequest.email,
                            password = password,
                            firstName = authRequest.firstName,
                            lastName = authRequest.lastName,
                            phone = authRequest.phoneNumber,
                            role = Role.CLIENT,
                            client = User.Client(
                                birthday = authRequest.birthDate
                            )
                        )
                        saveRoleToPreferences("client")

                        Log.i(MY_LOG, "current User is Client")
                    } else {
                        // Master
                        currentUser = User(
                            token = _userToken!!,
                            email = authRequest.email,
                            password = password,
                            firstName = authRequest.firstName,
                            lastName = authRequest.lastName,
                            phone = authRequest.phoneNumber,
                            role = Role.MASTER,
                            master = User.Master()
                        )
                        saveRoleToPreferences("master")

                        Log.i(MY_LOG, "current User is Master")
                    }
                    onResponse(true)
                } else {
                    Log.e(MY_LOG, "Registration failed: ${response.errorBody().toString()}")
                    Log.e(MY_LOG, "Try to reinstall app")
                    Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show()

                    logout()
                    onResponse(false)
                }
            } catch (e: Exception) {
                Log.d(MY_LOG, "Receive token exception")
                handleApiException(e)
                onResponse(false)
            }
            _isLoading.emit(false)
        }
    }

    fun authenticate(route: String, authRequest: LogInRequest, onResponse: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.emit(true)

            try {
                val response = apiService.authenticate(authRequest)
                if (response.isSuccessful && response.body()?.token != null) {
                    _userToken = response.body()!!.token
                    saveTokenToPreferences(_userToken!!)
                    Network.updateToken(_userToken)

                    loadCurrentUser(userRole = route, context = context, token = "Bearer $_userToken")
                    userIsAuthenticated.value = true

                    onResponse(true)
                } else {
                    Log.e(MY_LOG, "Authentication failed: ${response.errorBody().toString()}")
                    Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show()
                    onResponse(false)
                }

            } catch (e: Exception) {
                Log.d(MY_LOG, "Authorization exception")
                handleApiException(e)
                onResponse(false)
            }
            _isLoading.emit(false)
        }
    }

    fun activateAccount(code: String, onActivationComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.emit(true)

            try {
                Log.d(MY_LOG, "Activation started")
                val activationRequest = ActivationRequest(code)
                val response = apiService.activate("Bearer $_userToken", activationRequest)

                if (response.isSuccessful) {
                    Log.d(MY_LOG, "Account activated")
                    onActivationComplete(true)
                } else {
                    Toast.makeText(context, "Activation failed", Toast.LENGTH_SHORT).show()
                    Log.e(MY_LOG, "Activation failed: ${response.errorBody()?.string()}")
                    onActivationComplete(false)
                }
            } catch (e: Exception) {
                handleApiException(e)
                onActivationComplete(false)
            }
            _isLoading.emit(false)
        }
    }

    fun getServicesByCategoryId(
        categoryIds: List<CategoryResponse>,
        onComplete: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val responses = categoryIds.map { category ->
                    async {
                        category.id?.let { apiService.getCategoryServices(it) }
                    }
                }.awaitAll()

                val successfulResponses =
                    responses.filter { it!!.isSuccessful }.mapNotNull { it?.body() }.flatten()
                if (successfulResponses.isNotEmpty()) {
                    _servicesLiveDataClient.postValue(successfulResponses)
                    onComplete(true)
                } else {
                    onComplete(false)
                }
            } catch (e: Exception) {
                handleApiException(e)
                onComplete(false)
            }
            _isLoading.emit(false)
        }
    }

    fun uploadImage(file: File) {
        val requestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val multipartBody = MultipartBody.Part.createFormData("file", file.name, requestBody)

        viewModelScope.launch {
            try {
                val response = apiService.uploadProfilePicture(
                    token = "Bearer $_userToken",
                    file = multipartBody
                )
                if (response.isSuccessful) {
                    currentUser?.master?.profileImage = file
                    Log.d(MY_LOG, "Upload image success")
                } else {
                    Log.e(MY_LOG, "Upload image fail: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e(MY_LOG, "Exception. Upload image fail: ${e.message}")
            }
        }
    }

    fun uploadWorkPicture(file: File, context: Context) {
        val requestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val multipartBody = MultipartBody.Part.createFormData("file", file.name, requestBody)

        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val response = apiService.uploadMastersPicture(
                    token = "Bearer $_userToken",
                    picture = multipartBody
                )
                if (response.isSuccessful) {
                    currentUser?.master?.images?.add(file)
                    Log.d(MY_LOG, "Upload image success: ${response.body()}")
                    loadMasterWorks(context)
                } else {
                    Toast.makeText(context, "Upload image fail", Toast.LENGTH_SHORT).show()
                    Log.e(MY_LOG, "Upload image fail: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e(MY_LOG, "Exception. Upload image fail: ${e.message}")
            }
            _isLoading.emit(false)
        }
    }

    fun addCategory(categoryName: String, onCategoryAddComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val categoryIdResponse = apiService.addCategory(
                    "Bearer $_userToken",
                    CategoryRequest(name = categoryName, description = "")
                )
                if (categoryIdResponse.isSuccessful) {
                    val categoryId = categoryIdResponse.body()
                    if (categoryId != null) {
                        loadMasterCategories()

                        Log.i(MY_LOG, "Success to add category")
                        onCategoryAddComplete(true)
                    } else {
                        Log.e(MY_LOG, "category response is null")
                        onCategoryAddComplete(false)
                    }
                } else {
                    Log.e(MY_LOG, "Failed to add category")
                    onCategoryAddComplete(false)
                }
            } catch (e: Exception) {
                handleApiException(e)
                onCategoryAddComplete(false)
            }
            _isLoading.emit(false)
        }
    }

    fun addService(
        service: ServiceRequest,
        categoryId: Int,
        onServiceAddComplete: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                Log.i(MY_LOG, "categoryId is $categoryId")

                val response = apiService.addService("Bearer $_userToken", categoryId, service)
                if (response.isSuccessful) {
                    val idResponse = response.body()

                    if (idResponse != null) {
                        loadMasterServices()

                        Log.i(MY_LOG, "Service added successful")
                        onServiceAddComplete(true)
                    } else {
                        Log.e(MY_LOG, "service response is null")
                        onServiceAddComplete(false)
                    }
                }
            } catch (e: Exception) {
                handleApiException(e)
                onServiceAddComplete(false)
            }
            _isLoading.emit(false)
        }
    }

    fun editService(
        service: EditServiceRequest,
        id: Int,
        onServiceEditComplete: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val response = apiService.editService("Bearer $_userToken", id, service)
                if (response.isSuccessful) {
                    val body = response.body()?.string()

                    if (body != null) {
                        loadMasterServices()

                        Log.i(MY_LOG, "Service edit successful")
                        onServiceEditComplete(true)
                    } else {
                        Log.e(MY_LOG, "service response is null")
                        onServiceEditComplete(false)
                    }
                } else {
                    Log.e(MY_LOG, "Error is ${response.errorBody()}")

                }
            } catch (e: Exception) {
                handleApiException(e)
                onServiceEditComplete(false)
            }
            _isLoading.emit(false)
        }
    }

    fun editMasterProfile(
        newMasterInfo: EditMasterRequest,
        context: Context,
        onProfileEditComplete: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val response = apiService.editMasterProfile("Bearer $_userToken", newMasterInfo)
                if (response.isSuccessful) {
                    val body = response.body()

                    if (body != null) {
                        loadCurrentUser(_userToken!!, context, "Master")

                        Log.i(MY_LOG, "Master profile edit successful")
                        onProfileEditComplete(true)
                    } else {
                        Log.e(MY_LOG, "response is null")
                        onProfileEditComplete(false)
                    }
                } else {
                    Log.e(MY_LOG, "Error is ${response.errorBody()}")

                }
            } catch (e: Exception) {
                handleApiException(e)
                onProfileEditComplete(false)
            }
            _isLoading.emit(false)
        }
    }

    fun getMastersByIds(ids: List<Int>, onAddComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val responses = ids.map { id ->
                    async {
                        apiService.getUserById(id = id, token = "Bearer $_userToken")
                    }
                }.awaitAll()

                val masterResponses = responses.mapNotNull { response ->
                    if (response.isSuccessful) {
                        response.body()?.let { data ->
                            MasterForClient(
                                id = data.id,
                                fullName ="${data.firstName} ${data.lastName}" ,
                                description = data.master?.description,
                                address = data.master?.address,
                                masterId = data.master?.masterId,
                                messenger = data.master?.messenger,
                                profilePictureId = data.master?.profilePictureId,
                                additionalImagesIds = data.master?.additionalImagesIds,
                                phoneNumber = data.phone,
                                services = data.master?.services,
                                categories = data.master?.categories,
                                schedule = data.master?.schedule,
                                profileImage = null,
                                images = null
                            )
                        }
                    } else {
                        null
                    }
                }

                val newMasters = masterResponses.filter { master ->
                    _mastersForClient.value.none { it.id == master.id }
                }

                if (newMasters.isNotEmpty()) {
                    _mastersForClient.value += newMasters
                }

                onAddComplete(true)
            } catch (e: Exception) {
                handleApiException(e)
                onAddComplete(false)
            }
            _isLoading.emit(false)
        }
    }


    fun addMasterById(id: Int, onAddComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val response = apiService.getMasterById(id = id, token = "Bearer $_userToken")

                if (response.isSuccessful) {
                    response.body()?.let { data ->

                        val masterResponse = MasterForClient(
                            id = id,
                            fullName = data.fullName,
                            description = data.description,
                            address = data.address,
                            masterId = data.masterId,
                            messenger = data.messenger,
                            profilePictureId = data.profilePictureId,
                            additionalImagesIds = data.additionalImagesIds,
                            phoneNumber = data.phoneNumber,
                            services = data.services,
                            categories = data.categories,
                            schedule = data.schedule,

                            //TODO
                            profileImage = null,
                            images = null
                        )

                        //if (_mastersForClient.value.all { it.id != id }) {
                            Log.e(MY_LOG, "add + master, ${data.fullName}")
                            _mastersForClient.value += masterResponse
                        //}
                    }
                }
            } catch (e: Exception) {
                handleApiException(e)
                onAddComplete(false)
            }
            _isLoading.emit(false)
        }
    }

    fun pushMasterById(id: Int, onAddComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val response = apiService.addAddedMaster(
                    token = "Bearer $_userToken",
                    id = id,
                )
                if (response.isSuccessful) {
                    Log.e(MY_LOG, "responseAddedMaster is Successful")
                    onAddComplete(true)
                } else {
                    Log.e(MY_LOG, "Error is ${response.errorBody()?.string()}")
                    onAddComplete(false)
                }
            } catch (e: Exception) {
                Log.e(MY_LOG, "Exception occurred while adding master", e)
                onAddComplete(false)
            }
            _isLoading.emit(false)
        }
    }

    fun getImageById(id: Int, onAddComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.getImageById(id = id, token = "Bearer $_userToken")
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        Log.e(MY_LOG, "img is $data")
                    }
                }
            } catch (e: Exception) {
                handleApiException(e)
                onAddComplete(false)
            }
        }
    }

    fun getSchedulesById(masterId: Int, onAddComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response =
                    apiService.getSchedulesById(id = masterId, token = "Bearer $_userToken")
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        _schedulesLiveData.postValue(data)
                        Log.d(MY_LOG, "getSchedulesById is $data")
                        onAddComplete(true)
                    } ?: run {
                        onAddComplete(false)
                    }
                } else {
                    onAddComplete(false)
                }
            } catch (e: Exception) {
                handleApiException(e)
                onAddComplete(false)
            }
        }
    }

    fun getFreeTimeSlots(
        masterId: Int,
        serviceId: Int,
        date: String,
        onAddComplete: (Boolean) -> Unit
    ) {
        Log.i(MY_LOG, "getFreeTimeSlots masterId $masterId")
        Log.i(MY_LOG, "getFreeTimeSlots serviceId $serviceId")
        Log.i(MY_LOG, "getFreeTimeSlots date $date")

        viewModelScope.launch {
            try {
                val response = apiService.getFreeTimeSlots(
                    token = "Bearer $_userToken",
                    masterId = masterId,
                    serviceId = serviceId,
                    date = date
                )
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        _freeSlotsLiveData.postValue(data)
                        onAddComplete(true)
                    } ?: run {
                        onAddComplete(false)
                    }
                } else {
                    onAddComplete(false)
                }
            } catch (e: Exception) {
                handleApiException(e)
                onAddComplete(false)
            }
        }
    }

    fun getServiceByName(serviceName: String?): Service? {
        if (serviceName != null) {
            Log.d(MY_LOG, "currentService name is $serviceName")
            servicesLiveDataMaster.value?.forEach {
                Log.d(MY_LOG, "servicesLiveDataMaster ${it.name}")
            }

            return servicesLiveDataMaster.value?.firstOrNull {
                it.name.capitalize() == serviceName.capitalize()
            }
        }
        return null
    }

    fun deleteService(serviceId: Int, onServiceRemoveComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val response = apiService.removeService("Bearer $_userToken", serviceId)
                if (response.isSuccessful) {
                    val body = response.body()

                    if (body != null) {
                        loadMasterServices()

                        Log.i(MY_LOG, "Service remove successful")
                        onServiceRemoveComplete(true)
                    } else {
                        Log.e(MY_LOG, "Remove service response is null")
                        onServiceRemoveComplete(false)
                    }
                } else {
                    Log.e(MY_LOG, "Error occurred while removing service: ${response.errorBody()}")
                    onServiceRemoveComplete(false)
                }
            } catch (e: Exception) {
                handleApiException(e)
                onServiceRemoveComplete(false)
            }
            _isLoading.emit(false)
        }
    }

    fun deleteAllRecords(date: String, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val response = apiService.deleteAllRecords("Bearer $_userToken", date)
                if (response.isSuccessful) {
                    val body = response.body()

                    if (body != null) {
                        loadMasterRecords()

                        Log.i(MY_LOG, "Records remove successful")
                        onComplete(true)
                    } else {
                        Log.e(MY_LOG, "Remove Records response is null")
                        onComplete(false)
                    }
                } else {
                    Log.e(MY_LOG, "Error occurred while removing Records: ${response.errorBody()}")
                    onComplete(false)
                }
            } catch (e: Exception) {
                handleApiException(e)
                onComplete(false)
            }
            _isLoading.emit(false)
        }
    }

    fun deleteRecord(id: Int, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val response = apiService.deleteRecord("Bearer $_userToken", id)
                if (response.isSuccessful) {
                    val body = response.body()

                    if (body != null) {
                        loadMasterRecords()

                        Log.i(MY_LOG, "Records remove successful")
                        onComplete(true)
                    } else {
                        Log.e(MY_LOG, "Remove Records response is null")
                        onComplete(false)
                    }
                } else {
                    Log.e(MY_LOG, "Error occurred while removing Records: ${response.errorBody()}")
                    onComplete(false)
                }
            } catch (e: Exception) {
                handleApiException(e)
                onComplete(false)
            }
            _isLoading.emit(false)
        }
    }

    fun deleteCategory(categoryId: Int, onCategoryRemoveComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val response = apiService.deleteCategory("Bearer $_userToken", categoryId)
                if (response.isSuccessful) {
                    val body = response.body()

                    if (body != null) {
                        loadMasterCategories()

                        Log.i(MY_LOG, "Category remove successful")
                        onCategoryRemoveComplete(true)
                    } else {
                        Log.e(MY_LOG, "Remove category response is null")
                        onCategoryRemoveComplete(false)
                    }
                } else {
                    Log.e(MY_LOG, "Error occurred while removing category: ${response.errorBody()}")
                    onCategoryRemoveComplete(false)
                }
            } catch (e: Exception) {
                handleApiException(e)
                onCategoryRemoveComplete(false)
            }
            _isLoading.emit(false)
        }
    }

    fun getMasterCode(onComplete: (String?) -> Unit) {
        viewModelScope.launch {
            _isLoading.emit(true)
            val masterId = currentUser?.id

            val masterCode = if (masterId != null) {
                generateCode(masterId)
            } else {
                null
            }

            onComplete(masterCode)
            _isLoading.emit(false)
        }
    }

    fun editCategory(
        id: Int,
        category: CategoryRequest,
        onCategoryEditComplete: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val response = apiService.editCategory("Bearer $_userToken", id, category)
                if (response.isSuccessful) {
                    val body = response.body()?.string()

                    if (body != null) {
                        loadMasterCategories()

                        Log.i(MY_LOG, "Category edit successful")
                        onCategoryEditComplete(true)
                    } else {
                        Log.e(MY_LOG, "Category response is null")
                        onCategoryEditComplete(false)
                    }
                } else {
                    Log.e(MY_LOG, "Error is ${response.errorBody()}")
                }
            } catch (e: Exception) {
                handleApiException(e)
                onCategoryEditComplete(false)
            }
            _isLoading.emit(false)
        }
    }

    fun getCategoryByName(selectedCategoryName: String?): Category? {
        try {
            return categoriesLiveDataMaster.value?.first { it.name == selectedCategoryName }
        } catch (_: Exception) {
        }
        return null
    }

    fun getService(serviceId: Int): Service {
        val service = servicesLiveDataMaster.value?.first { it.id == serviceId }

        if (service == null) {
            return Service(
                Random.nextInt(),
                "",
                "",
                0,
                Duration(0, 0),
                Category(Random.nextInt(), "", "")
            )
        } else {
            return service
        }
    }

    fun createSchedule(scheduleCreateRequest: ScheduleCreateRequest) {
        viewModelScope.launch {
            try {
                val response =
                    apiService.createSchedule("Bearer $_userToken", scheduleCreateRequest)
                if (response.isSuccessful) {
                    getScheduleByToken {}

                    Log.d(MY_LOG, "Schedule created successfully")
                } else {
                    Log.e(MY_LOG, "Schedule create fail: " + response.errorBody())
                }
            } catch (_: Exception) {
            }
        }
    }

    fun getScheduleByToken(onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val response = apiService.getScheduleByToken(token = "Bearer $_userToken")
                if (response.isSuccessful) {

                    val data = response.body()
                    if (data != null) {
                        _schedulesLiveData.postValue(data!!)
                        onComplete(true)
                    } else {
                        _schedulesLiveData.postValue(emptyList())
                        onComplete(false)
                    }

                } else {
                    Log.e(MY_LOG, "Error is ${response.errorBody()}")
                }
            } catch (e: Exception) {
                handleApiException(e)
                onComplete(false)
            }
            _isLoading.emit(false)
        }
    }

    fun deleteSchedule(dates: List<String>, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val response =
                    apiService.deleteSchedule(token = "Bearer $_userToken", dates = dates)
                if (response.isSuccessful) {
                    val data = response.body()
                    getScheduleByToken {}
                    if (data != null) onComplete(true) else onComplete(false)
                } else {
                    Log.e(MY_LOG, "Error is ${response.errorBody()}")
                }
            } catch (e: Exception) {
                handleApiException(e)
                onComplete(false)
            }
            _isLoading.emit(false)
        }
    }

    fun getRecords(onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val response = apiService.getRecords(token = "Bearer $_userToken")
                if (response.isSuccessful) {

                    val records = response.body()
                    if (records != null) {
                        _recordsLiveData.postValue(records!!)
                        onComplete(true)
                    } else {
                        _recordsLiveData.postValue(emptyList())
                        onComplete(false)
                    }

                } else {
                    Log.e(MY_LOG, "Error is ${response.errorBody()}")
                }
            } catch (e: Exception) {
                handleApiException(e)
                onComplete(false)
            }
            _isLoading.emit(false)
        }
    }

    fun createRecord(
        id: Int,
        bookingRequest: BookingRequest,
        onComplete: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val response = apiService.createRecord("Bearer $_userToken", id, bookingRequest)
                if (response.isSuccessful) {
                    val body = response.body()?.string()

                    if (body != null) {
                        loadMasterCategories()

                        Log.i(MY_LOG, "createRecord successful")
                        onComplete(true)
                    } else {
                        Log.e(MY_LOG, "createRecord is null")
                        onComplete(false)
                    }
                } else {
                    Log.e(MY_LOG, "Error is ${response.errorBody()}")
                }
            } catch (e: Exception) {
                handleApiException(e)
                onComplete(false)
            }
            _isLoading.emit(false)
        }
    }

    fun logout(isExit: Boolean = false) {
        Log.d(MY_LOG, "Logging out user")

        preferences.edit().remove("token").commit()
        preferences.edit().remove("role").commit()

        _userRole = null
        _userToken = null
        currentUser = null

        userIsAuthenticated.value = false
        Network.updateToken(null)

        if (isExit) {
            if (context is MainActivity) {
                context.restartApp()
            }
            //            Handler(Looper.getMainLooper()).postDelayed({
//                exitProcess(0)
//            }, 100)
        }
    }

    // ---------------- PRIVATE FUN ------------------------------------------------
    private fun loadCurrentUser(token: String, context: Context, userRole: String) {
        viewModelScope.launch {

            val response = apiService.getUserByToken(token)
            if (response.isSuccessful) {
                Log.d(MY_LOG, "User loaded successfully")
                currentUser = response.body()
                saveRoleToPreferences(userRole.toLowerCase())

                try {
                    if (userRole.capitalize() == "Master") {
                        loadMasterServices()
                        loadMasterCategories()
                        loadMasterImage(context)
                        loadMasterWorks(context)
                        loadMasterRecords()
                    }
                    else if (userRole.capitalize() == "Client") {
                        if (currentUser?.client?.mastersIds != null) {
                            getMastersByIds(ids = currentUser!!.client!!.mastersIds) {}
                        }
                    }
                } catch (e: Exception) {
                    Log.e(
                        MY_LOG,
                        "Exception in loadMasterServices() loadMasterCategories()"
                    )
                }

                try {
                    getScheduleByToken {}
                } catch (e: Exception) {
                }

            } else Log.e(MY_LOG, "Failed to load user")
        }
    }

    private fun saveTokenToPreferences(token: String) {
        Log.d(MY_LOG, "Saving token to shared preferences")
        preferences.edit().apply {
            putString("token", token)
            apply()
        }
    }

    private fun saveRoleToPreferences(role: String) {
        Log.d(MY_LOG, "Saving role to shared preferences")
        preferences.edit().apply {
            putString("role", role)
            apply()
        }
    }

    private fun handleApiException(e: Exception) = when (e) {
        is SocketTimeoutException -> {
            Log.e(MY_LOG, "API request timed out")
        }

        else -> {
            Log.e(MY_LOG, "Unknown API error occurred: ${e.localizedMessage}")
        }
    }

    private suspend fun loadMasterServices() {
        val response = apiService.getMasterServicesByToken("Bearer $_userToken")
        if (response.isSuccessful) {
            val services = response.body()
            if (services != null) {
                servicesLiveDataMaster.postValue(services.reversed())
            } else {
                servicesLiveDataMaster.postValue(emptyList())
            }
        } else {
            Log.d(MY_LOG, "Failed to load services")
        }
    }

    private suspend fun loadMasterRecords() {
        val response = apiService.getRecords(token = "Bearer $_userToken")
        if (response.isSuccessful) {
            Log.e(MY_LOG, "getRecords is Successful")
            Log.e(MY_LOG, response.body()?.first()?.status.toString())

            val records = response.body()
            if (records != null) {
                _recordsLiveData.postValue(records!!)
            } else {
                _recordsLiveData.postValue(emptyList())
            }
        } else {
            Log.e(MY_LOG, "Error is ${response.errorBody()?.string()}")
        }
    }

    private fun saveResponseBodyToDisk(
        responseBody: ResponseBody,
        context: Context,
        fileName: String
    ): File? {
        return try {
            val file = File(context.cacheDir, fileName)
            var inputStream: InputStream? = null
            var outputStream: FileOutputStream? = null

            try {
                inputStream = responseBody.byteStream()
                outputStream = FileOutputStream(file)
                val buffer = ByteArray(4096)
                var read: Int

                while (inputStream.read(buffer).also { read = it } != -1) {
                    outputStream.write(buffer, 0, read)
                }

                outputStream.flush()
                file
            } catch (e: Exception) {
                Log.e(MY_LOG, "Failed to save file: ${e.message}")
                null
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: Exception) {
            Log.e(MY_LOG, "Error writing file to disk: ${e.message}")
            null
        }
    }

    private fun generateCode(specificDigit: Int?): String {
        if (specificDigit == null) return "Something went wrong. Please try agan"

        fun getRandomChar(): Char {
            val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
            return chars.random()
        }

        val firstPart = (1..7).map { getRandomChar() }.joinToString("")
        val masterCode = firstPart + specificDigit

        return masterCode
    }

    private suspend fun loadMasterWorks(context: Context) {
        val response = apiService.getMastersWorksId("Bearer $_userToken")
        if (response.isSuccessful) {
            val imageIds = response.body()
            imageIds?.let {
                Log.d(MY_LOG, "Fetched images id: $it")
                val imageFiles = mutableListOf<File>()

                for (id in it) {
                    val picResponse = apiService.getMastersWorkById(id)
                    if (picResponse.isSuccessful) {
                        picResponse.body()?.let { responseBody ->
                            val fileName = "image_$id.jpeg"
                            val imageFile = saveResponseBodyToDisk(responseBody, context, fileName)
                            imageFile?.let { file ->
                                imageFiles.add(file)
                            }
                        }
                    } else {
                        Log.d(MY_LOG, "Failed to fetch image for id: $id")
                    }
                }

                currentUser?.master?.images = imageFiles.toMutableList()
            }
        } else {
            Log.d(MY_LOG, "Failed to loadMastersWorks")
        }
    }

    private suspend fun loadMasterCategories() {
        val response = apiService.getMasterCategoriesByToken("Bearer $_userToken")
        if (response.isSuccessful) {
            val categories = response.body()
            if (categories != null) {
                categoriesLiveDataMaster.postValue(categories.reversed())
            } else {
                categoriesLiveDataMaster.postValue(emptyList())
            }
        } else {
            Log.d(MY_LOG, "Failed to load categories")
        }
    }

    private suspend fun loadMasterImage(context: Context) {
        val response = apiService.getProfilePicture("Bearer $_userToken")
        if (response.isSuccessful) {
            response.body()?.let { responseBody ->
                val bitmap = BitmapFactory.decodeStream(responseBody.byteStream())
                val file = saveBitmapToFile(bitmap, context)
                file?.let {
                    currentUser?.master?.profileImage = it
                }
            }
            Log.d(MY_LOG, "Success to loadMasterImage")

        } else {
            Log.d(MY_LOG, "Failed to loadMasterImage")
        }
    }

    private fun saveBitmapToFile(bitmap: Bitmap, context: Context): File? {
        val file = File(context.cacheDir, "profile_picture.png")
        try {
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            return file
        } catch (e: Exception) {
            Log.e(MY_LOG, "Failed to save bitmap to file: ${e.message}")
            return null
        }
    }
}