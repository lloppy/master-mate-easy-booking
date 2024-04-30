package com.example.skills.repository

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.skills.data.User


// 1
class MainViewModel: ViewModel() {

    var appJustLaunched by mutableStateOf(true)
    var userIsAuthenticated by mutableStateOf(false)

    private val TAG = "MainViewModel"
    private lateinit var account: String
    private lateinit var context: Context

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

    fun setContext(activityContext: Context) {
        context = activityContext
    }

}

