package com.example.skills.data.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.skills.data.api.Network.apiService
import com.example.skills.data.roles.User
import kotlinx.coroutines.launch

const val MY_LOG = "MY_LOG"

class MainViewModel(context: Context) : ViewModel() {
    var currentUser: User? by mutableStateOf(null)
    var userIsAuthenticated by mutableStateOf(false)

    private val preferences: SharedPreferences = context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
    fun saveUserCredentials(token: String) {
        Log.d(MY_LOG, "Saving user credentials to shared preferences")
        preferences.edit().apply {
            putString("token", token)
            apply()
        }
        Log.d(MY_LOG, "Loading current user info")
        loadCurrentUser(token)
        userIsAuthenticated = true
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

    fun readUserCredentials() {
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

    fun logout() {
        Log.e(MY_LOG, "Logging out user")
        preferences.edit().apply {
            remove("token")
            apply()
        }
        currentUser = null
        userIsAuthenticated = false
    }

    init {
        Log.d(MY_LOG, "Initializing ViewModel, reading user credentials")
        readUserCredentials()
    }
}