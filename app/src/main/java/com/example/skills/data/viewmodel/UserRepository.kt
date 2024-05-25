package com.example.skills.data.viewmodel

import android.content.SharedPreferences
import com.example.skills.data.api.ApiService
import com.example.skills.data.roles.User

class UserRepository(private val apiService: ApiService, private val preferences: SharedPreferences) {
    suspend fun getCurrentUser(): User? {
        val token = preferences.getString("token", null) ?: return null
        val response = apiService.getUserByToken(token)
        return if (response.isSuccessful) response.body() else null
    }
}