package com.example.skills.data

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


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


    fun getMastersList(): List<Master> {
        return listMasters
    }

    fun getMaster(id: Long): Master {
        val master = listMasters.first { it.id == id }
        return master
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


    val imageUrls = listOf(
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
    )

    val listMasters = listOf(
        Master(
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
        ), Master(
            223,
            "Екатерина@gmail.com",
            "Екатерина",
            "Иванова",
            "79503223232",
            null,
            "12345",
            Role.MASTER,
            "Ведущий мастер в области макияжа и стилистики с более чем десятилетним опытом. Сотрудничал с известными брендами, работала на крупнейших модных показах и обучала начинающих визажистов.",
            "https://t.me/lloppy",
            "г.Екатеринбург, ул.Фонвизина, д.6",
            imageUrls
        ), Master(
            323,
            "Вероника@gmail.com",
            "Вероника",
            "Дуброва",
            "79503223232",
            null,
            "12345",
            Role.MASTER,
            "Ведущий мастер в области макияжа и стилистики с более чем десятилетним опытом. Сотрудничал с известными брендами, работала на крупнейших модных показах и обучала начинающих визажистов.",
            "https://t.me/lloppy",
            "г.Екатеринбург, ул.Фонвизина, д.6",
            imageUrls
        ),
        Master(
            423,
            "Пётр@gmail.com",
            "Пётр",
            "Высоков",
            "79503223232",
            null,
            "12345",
            Role.MASTER,
            "Ведущий мастер в области макияжа и стилистики с более чем десятилетним опытом. Сотрудничал с известными брендами, работала на крупнейших модных показах и обучала начинающих визажистов.",
            "https://t.me/lloppy",
            "г.Екатеринбург, ул.Фонвизина, д.6",
            imageUrls
        )
    )
}

