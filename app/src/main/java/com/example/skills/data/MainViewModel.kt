package com.example.skills.data

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.skills.data.Master
import com.example.skills.data.Role


// 1
class MainViewModel : ViewModel() {

    var appJustLaunched by mutableStateOf(true)
    var userIsAuthenticated by mutableStateOf(false)

    private val TAG = "MainViewModel"
    private lateinit var account: String
    private lateinit var context: Context

    // var user by mutableStateOf(User())
    fun getMaster(): Master {
        val imageUrls = listOf(
            Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
            Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
            Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
            Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
            Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
            Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
            Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
            Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
            Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
            Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
            Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
            Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
               )

        return Master(
            123,
            "masterivan@gmail.com",
            "Иван",
            "Коссе",
            "79503223232",
            null,
            "12345",
            Role.MASTER,
            "Ведущий мастер в области макияжа и стилистики с более чем десятилетним опытом. Сотрудничал с известными брендами, работала на крупнейших модных показах и обучала начинающих визажистов.",
            "https://t.me/lloppy",
            "г.Екатеринбург, ул.Фонвизина, д.6",
            imageUrls
        )
    }

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

