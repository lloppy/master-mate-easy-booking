package com.example.skills.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {
    var appJustLaunched by mutableStateOf(true)
    var userIsAuthenticated by mutableStateOf(false)

    private val TAG = "MainViewModel"
    private lateinit var account: String

    // var user by mutableStateOf(User())

    fun login() {
//        if (isSucsess){
//            val idToken = result.idToken
//
//            Log.d(TAG, "ID token: $idToken")
//
//            user = User(idToken)
        userIsAuthenticated = true
        appJustLaunched = false
//        }
    }

    fun logout() {
        // user = User()

        userIsAuthenticated = false
    }
}
