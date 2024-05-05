package com.example.skills.data

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.skills.master.components.d.Category
import com.example.skills.master.components.d.SingleService


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

    val categories = listOf<Category>(
        Category(
            "Категория 1",
            {},
            singlesCategory = listOf(
                SingleService(
                    12345,
                    "Маникюр классический",
                    "Кутикула аккуратно отодвигается специальным апельсиновым палочкой или мягким пушером, что позволяет избежать порезов и предотвратить возможное воспаление. Этот метод идеален для тех, кто стремится поддерживать ногти в отличном состоянии без риска инфекции.",
                    800,
                    45
                ),
                SingleService(
                    123456,
                    "Маникюр европейский",
                    "Кутикула аккуратно отодвигается специальным апельсиновым палочкой или мягким пушером. Чтобы размягчить грубую кутикулу, ее можно регулярно смазывать",
                    1000,
                    55
                ),
                SingleService(
                    12344567,
                    "Маникюр классический",
                    "Процесс включает в себя увлажнение и массаж рук, обработку кутикулы, подпиливание и придание им красивой формы, удаление кутикулы, нанесение крема для ухода за руками и масла для ухода за кутикулой.",
                    800,
                    45
                )
            )
        ),
        Category(
            "Категория 2", {},
            singlesCategory = listOf(
                SingleService(
                    2345,
                    "Педикюр",
                    "Расслабляющая ванночка для ног, обработка кутикулы, коррекция формы ногтей, удаление огрубевшей кожи",
                    1200,
                    75
                )
            )
        ),
        Category("Категория 3", {}),
    )

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
            imageUrls,
            categories
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
            imageUrls,
            categories
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
            imageUrls,
            categories
        )
    )

    fun findService(serviceId: Long): SingleService {
        return categories
            .flatMap { it.singlesCategory!! }
            .find { it.serviceId == serviceId }!!
    }

}


