package com.example.skills.data.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.skills.data.Master
import com.example.skills.data.Role
import com.example.skills.data.models.Category
import com.example.skills.data.models.Service


// 1
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

fun getMaster(id: Long): Master = listMasters.first { it.id == id }
fun getMaster(): Master = listMasters.first()
fun getMastersList(): List<Master> = listMasters
fun findService(serviceId: Long): Service = services.find { it.serviceId == serviceId }!!

val categories = listOf<Category>(
    Category("Категория 1", {}),
    Category("Категория 2", {}),
    Category("Категория 3", {})
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

val services: List<Service> = listOf(
    Service(
        serviceId = 12345,
        master = listMasters.first(),
        name = "Маникюр классический",
        description = "Кутикула аккуратно отодвигается специальным апельсиновым палочкой или мягким пушером, что позволяет избежать порезов и предотвратить возможное воспаление. Этот метод идеален для тех, кто стремится поддерживать ногти в отличном состоянии без риска инфекции.",
        price = 800,
        duration = 45,
        category = categories.first()
    ),
    Service(
        serviceId = 123456,
        master = listMasters.first(),
        name = "Маникюр европейский",
        description = "Кутикула аккуратно отодвигается специальным апельсиновым палочкой или мягким пушером. Чтобы размягчить грубую кутикулу, ее можно регулярно смазывать",
        price = 1000,
        duration = 55,
        category = categories.first()
    ),
    Service(
        12344567,
        master = listMasters.last(),
        "Маникюр классический",
        "Процесс включает в себя увлажнение и массаж рук, обработку кутикулы, подпиливание и придание им красивой формы, удаление кутикулы, нанесение крема для ухода за руками и масла для ухода за кутикулой.",
        800,
        45,
        category = categories[1]
    ),
    Service(
        2345,
        listMasters.last(),
        "Педикюр",
        "Расслабляющая ванночка для ног, обработка кутикулы, коррекция формы ногтей, удаление огрубевшей кожи",
        1200,
        75,
        category = categories[1]
    )
)
