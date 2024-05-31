package com.example.skills.data.viewmodel

import android.net.Uri
import com.example.skills.data.entity.Address
import com.example.skills.data.roles.Role
import com.example.skills.data.entity.Category
import com.example.skills.data.entity.RecordItem
import com.example.skills.data.entity.Service
import com.example.skills.data.entity.RecordStatus
import com.example.skills.data.roles.User
import java.time.LocalDateTime

object MyRepository {
//    private val categories = listOf<Category>(
//        Category("Категория 1", description = ""),
//        Category("Категория 2", description = ""),
//        Category("Категория 3", description = "")
//    )

    private val imageUrls = listOf(
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
    )


    private val listMasters = listOf(
        User(
            "123",
            "masterivan@gmail.com",
            "79503223232",
            "Иван",
            "Коссе",
            "90303003",
            Role.MASTER,
            master = User.Master(
                profileImageId = "Ведущий мастер в области макияжа и стилистики с более чем десятилетним опытом. Сотрудничал с известными брендами, работала на крупнейших модных показах и обучала начинающих визажистов.",
                messenger = "https://t.me/lloppy",
                address = Address("Россия", "Москва", "Маяковская", "10", "2"),
                images = imageUrls
            )
        )
    )
//
//    private val services: List<Service> = listOf(
//        Service(
//            serviceId = 123,
//            master = listMasters.first(),
//            name = "Маникюр классический",
//            description = "Кутикула аккуратно отодвигается специальным апельсиновым палочкой или мягким пушером, что позволяет избежать порезов и предотвратить возможное воспаление. Этот метод идеален для тех, кто стремится поддерживать ногти в отличном состоянии без риска инфекции.",
//            price = 800,
//            duration = 45,
//            category = categories.first()
//        ),
//        Service(
//            serviceId = 123456,
//            master = listMasters.first(),
//            name = "Маникюр европейский",
//            description = "Кутикула аккуратно отодвигается специальным апельсиновым палочкой или мягким пушером. Чтобы размягчить грубую кутикулу, ее можно регулярно смазывать",
//            price = 1000,
//            duration = 55,
//            category = categories.first()
//        ),
//        Service(
//            12344567,
//            master = listMasters.last(),
//            "Маникюр классический",
//            "Процесс включает в себя увлажнение и массаж рук, обработку кутикулы, подпиливание и придание им красивой формы, удаление кутикулы, нанесение крема для ухода за руками и масла для ухода за кутикулой.",
//            800,
//            45,
//            category = categories[1]
//        ),
//        Service(
//            2345,
//            listMasters.last(),
//            "Педикюр",
//            "Расслабляющая ванночка для ног, обработка кутикулы, коррекция формы ногтей, удаление огрубевшей кожи",
//            1200,
//            75,
//            category = categories[1]
//        )
//    )

    private val recordsItemList = mutableListOf<RecordItem>(
        RecordItem(
            serviceName = "Маникюр класический",
            price = 800,
            timeFrom = LocalDateTime.now().minusDays(10L),
            duration = 60,
            comment = "Опоздаю на 10 минут",
            clientName = "Анкудинова Полина",
            clientAge = 20,
            recordStatus = RecordStatus.ACTUAL
        ),
        RecordItem(
            "Маникюр класический",
            800,
            LocalDateTime.now(),
            60,
            "Анкудинова Полина",
            20,
            RecordStatus.ACTUAL
        ),
        RecordItem(
            "Маникюр класический",
            800,
            LocalDateTime.now().plusDays(4L),
            60,
            "Анкудинова Полина",
            20,
            RecordStatus.ACTUAL
        ),
        RecordItem(
            "Маникюр класический",
            800,
            LocalDateTime.now(),
            60,
            "Анкудинова Полина",
            20,
            RecordStatus.ACTUAL
        ),
        RecordItem(
            "Маникюр класический",
            800,
            LocalDateTime.now(),
            60,
            "Анкудинова Полина",
            20,
            RecordStatus.ACTUAL
        ),
        RecordItem(
            "Маникюр европейский",
            1000,
            LocalDateTime.now(),
            75,
            "Гиязов Арсель",
            20,
            RecordStatus.ARCHIVE,
            isDone = true
        ),
        RecordItem(
            "Маникюр европейский",
            1000,
            LocalDateTime.now(),
            75,
            "Гиязов Арсель",
            20,
            RecordStatus.ARCHIVE
        )
    )


    fun getMaster(id: String): User = listMasters.first { it.token == id }
    fun getMaster(): User = listMasters.first()
    fun getMastersList(): List<User> = listMasters
    fun getRecordsItemList(): MutableList<RecordItem> = recordsItemList
    //fun getServices(): List<Service> = services
}