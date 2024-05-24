package com.example.skills.data.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.system.Os.remove
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.skills.data.User

class MainViewModel(private val context: Context) : ViewModel() {
    lateinit var currentUser: User

    var userIsAuthenticated by mutableStateOf(false)

    private val preferences: SharedPreferences = context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)

    fun saveUserCredentials(username: String, password: String) {
        preferences.edit().apply {
            putString("username", username)
            putString("password", password)
            apply()
        }
        userIsAuthenticated = true
    }

    fun readUserCredentials() {
        val username = preferences.getString("username", null)
        val password = preferences.getString("password", null)
        if (username != null && password != null) {
         //   currentUser = getUserByToken()
            userIsAuthenticated = true
        } else {
            userIsAuthenticated = false
        }
    }

//    private fun getUserByToken(token: String): User {
//        return User()
//    }

    fun logout() {
        preferences.edit().apply {
            remove("username")
            remove("password")
            apply()
        }
        userIsAuthenticated = false
    }

    init {
        readUserCredentials()
    }
}