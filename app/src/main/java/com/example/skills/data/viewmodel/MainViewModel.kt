package com.example.skills.data.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skills.data.api.ActivationRequest
import com.example.skills.data.api.AuthRequest
import com.example.skills.data.api.Network
import com.example.skills.data.api.Network.apiService
import com.example.skills.data.roles.Role
import com.example.skills.data.roles.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.SocketTimeoutException

const val MY_LOG = "MY_LOG"

class MainViewModel(context: Context) : ViewModel() {
    var currentUser: User? by mutableStateOf(null)
        private set

    private var _userToken: String? = null
        private set

    private var _userRole: String? = null
        private set

    val userIsAuthenticated = mutableStateOf(false)
    private val preferences: SharedPreferences =
        context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)

    private val _isLoading = MutableStateFlow(false)
    var isLoading: StateFlow<Boolean> = _isLoading

    init {
        Log.d(MY_LOG, "Initializing ViewModel, reading user credentials")
        _userToken = preferences.getString("token", null)
        _userRole = preferences.getString("role", null)
        if (_userRole != null) {
            Log.i(MY_LOG, "👋 Init session. Role is $_userRole, token is $_userToken")

            _userToken?.let {
                Network.updateToken(it)
                userIsAuthenticated.value = true
                loadCurrentUser(it)
            }
        } else {
            Log.d(MY_LOG, "Gg, it`s old deprecated session 💩💩")
        }
    }

    fun registerUser(authRequest: AuthRequest, onResponse: (Boolean) -> Unit) {
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
                            password = authRequest.password,
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
                            password = authRequest.password,
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

    fun uploadImage(context: Context, selectedImage: Uri) {
        val contentResolver = context.contentResolver
        val parcelFileDescriptor = contentResolver.openFileDescriptor(selectedImage, "r", null)
        val inputStream = FileInputStream(parcelFileDescriptor!!.fileDescriptor)
        val file = File(context.cacheDir, getFileName(context, selectedImage))
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)

        val requestFile = file.asRequestBody(contentResolver.getType(selectedImage)!!.toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("profilePicture", file.name, requestFile)

        viewModelScope.launch {
            val response = apiService.uploadProfilePicture("Bearer $_userToken", body)
            if (response.isSuccessful) {
                currentUser!!.master!!.profileImageId = response.body()
                Log.d(MY_LOG, "Upload image success")
            } else {
                Log.e(MY_LOG, "Upload image fail")
            }
        }
    }

    fun getProfilePicture() {
        viewModelScope.launch {
            try {
                val response = apiService.getProfilePicture(token = "Bearer $_userToken")
                if (response.isSuccessful) {
                    currentUser!!.master!!.profileImageId = response.body()
                } else {
                    Log.e(MY_LOG, "Get image fail, response is not Successful")
                }
            } catch (e: Exception) {
                Log.e(MY_LOG, "Exception. Get image fail: ${e.message}")
            }
        }
    }

    fun logout() {
        Log.d(MY_LOG, "Logging out user")
        preferences.edit().remove("token").apply()
        _userRole = null

        _userToken = null
        currentUser = null

        userIsAuthenticated.value = false
        Network.updateToken(null)
    }

    // ---------------- PRIVATE FUN ------------------------------------------------
    private fun loadCurrentUser(token: String) {
        viewModelScope.launch {
            Log.d(MY_LOG, "Attempting to load user by token")
            val response = apiService.getUserByToken("Bearer $token")

            if (response.isSuccessful) {
                Log.d(MY_LOG, "User loaded successfully")
                currentUser = response.body()
            } else Log.e(MY_LOG, "Failed to load master")
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

    private fun getFileName(context: Context, uri: Uri): String {
        var name = ""
        val returnCursor = context.contentResolver.query(uri, null, null, null, null)
        if (returnCursor != null) {
            val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            returnCursor.moveToFirst()
            name = returnCursor.getString(nameIndex)
            returnCursor.close()
        }
        return name
    }
}