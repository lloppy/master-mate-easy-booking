package com.example.skills.data.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.skills.data.api.ActivationRequest
import com.example.skills.data.api.ActivationResponse
import com.example.skills.data.api.AuthRequest
import com.example.skills.data.api.Network
import com.example.skills.data.api.Network.apiService
import com.example.skills.data.roles.User
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

const val MY_LOG = "MY_LOG"

class MainViewModel(context: Context) : ViewModel() {
    var currentUser: User? by mutableStateOf(null)
    var userToken: String? by mutableStateOf(null)
    var userIsAuthenticated by mutableStateOf(false)

    private val preferences: SharedPreferences = context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)

    fun activateAccount(code: String, onActivationComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                Log.i(MY_LOG, userToken.toString())

                val activationRequest = ActivationRequest(code)
                val response = apiService.activate("Bearer $userToken", activationRequest)

                if (response.isSuccessful) {
                    Log.d(MY_LOG, "activateAccount isSuccessful ${response.body()?.status}")
                    onActivationComplete(true)
                } else {
                    Log.e(MY_LOG, "activateAccount - Server returned an error: ${response.errorBody()?.string()}")
                    onActivationComplete(false)
                }
            } catch (e: Exception) {
                Log.e(MY_LOG,"Exception occurred in activateAccount: ${e.message}")
                onActivationComplete(false)
            }
        }
    }

    fun registerUser(authRequest: AuthRequest, onResponse: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.register(authRequest)

                if (response.isSuccessful && response.body()!!.token != null) {
                    Log.i(MY_LOG, "body code is ${response.body()!!.token} ${response.body()}")
                    saveUserCredentials(response.body()!!.token)
                    userToken = response.body()!!.token
                    onResponse(true)
                } else {
                    Log.e(MY_LOG, "Server returned an error: ${response.errorBody()}")
                    onResponse(false)
                }
            } catch (e: Exception) {
                when (e) {
                    is SocketTimeoutException -> {
                        Log.e(MY_LOG, "API request timed out")
                    }
                    else -> {
                        Log.e(MY_LOG, "Unknown API error occurred: ${e.localizedMessage}")
                    }
                }
                onResponse(false)
            }
        }
    }

    fun logout() {
        Log.e(MY_LOG, "Logging out user")
        preferences.edit().apply {
            remove("token")
            apply()
        }
        this.userToken = null
        currentUser = null
        userIsAuthenticated = false
    }

    init {
        Log.d(MY_LOG, "Initializing ViewModel, reading user credentials")
        readUserCredentials()

    }

    private fun loadCurrentUser(token: String) {
        viewModelScope.launch {
            Log.d(MY_LOG, "Attempting to load user by token")
            val user = apiService.getUserByToken(token)
            if (user.isSuccessful) {
                Log.d(MY_LOG, "User loaded successfully")
                currentUser = user.body()
            } else {
                Log.e(MY_LOG, "Failed to load user")
            }
        }
    }

    private fun saveUserCredentials(token: String) {
        Log.d(MY_LOG, "Saving user credentials to shared preferences")
        preferences.edit().apply {
            putString("token", token)
            apply()
        }
        Log.d(MY_LOG, "Loading current user info")
        loadCurrentUser(token)
        userIsAuthenticated = true
    }

    private fun readUserCredentials() {
        val token = preferences.getString("token", null)
        if (token != null) {
            Log.d(MY_LOG, "Token found in shared preferences, loading user")
            loadCurrentUser(token)
            userIsAuthenticated = true
        } else {
            Log.e(MY_LOG, "No token found in shared preferences")
            userIsAuthenticated = false
        }
    }
}