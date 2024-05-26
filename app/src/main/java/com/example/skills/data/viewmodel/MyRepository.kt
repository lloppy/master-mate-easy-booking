package com.example.skills.data.viewmodel

import android.net.Uri
import com.example.skills.data.roles.Address
import com.example.skills.data.roles.Master
import com.example.skills.data.roles.Role
import com.example.skills.data.entity.Category
import com.example.skills.data.entity.RecordItem
import com.example.skills.data.entity.Service
import com.example.skills.data.entity.RecordStatus
import java.time.LocalDateTime

object MyRepository {
    private val categories = listOf<Category>(
        Category("Категория 1", action = {}),
        Category("Категория 2", action = {}),
        Category("Категория 3", action = {})
    )

    private val imageUrls = listOf(
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
    )

    private val listMasters = listOf(
        Master(
            "123",
            "masterivan@gmail.com",
            "Иван",
            "Коссе",
            "79503223232",
            null,
            "12345",
            Role.MASTER,
            "Ведущий мастер в области макияжа и стилистики с более чем десятилетним опытом. Сотрудничал с известными брендами, работала на крупнейших модных показах и обучала начинающих визажистов.",
            "https://t.me/lloppy",
            Address("Россия", "Москва", "Маяковская", "10", "2"),
            imageUrls
        ), Master(
            "223",
            "Екатерина@gmail.com",
            "Екатерина",
            "Иванова",
            "79503223232",
            null,
            "12345",
            Role.MASTER,
            "Ведущий мастер в области макияжа и стилистики с более чем десятилетним опытом. Сотрудничал с известными брендами, работала на крупнейших модных показах и обучала начинающих визажистов.",
            "https://t.me/lloppy",
            Address("Россия", "Москва", "Маяковская", "10", "2"),
            imageUrls
        ), Master(
            "323",
            "Вероника@gmail.com",
            "Вероника",
            "Дуброва",
            "79503223232",
            null,
            "12345",
            Role.MASTER,
            "Ведущий мастер в области макияжа и стилистики с более чем десятилетним опытом. Сотрудничал с известными брендами, работала на крупнейших модных показах и обучала начинающих визажистов.",
            "https://t.me/lloppy",
            Address("Россия", "Москва", "Маяковская", "10", "2"),
            imageUrls
        ),
        Master(
            "423",
            "Пётр@gmail.com",
            "Пётр",
            "Высоков",
            "79503223232",
            null,
            "12345",
            Role.MASTER,
            "Ведущий мастер в области макияжа и стилистики с более чем десятилетним опытом. Сотрудничал с известными брендами, работала на крупнейших модных показах и обучала начинающих визажистов.",
            "https://t.me/lloppy",
            Address("Россия", "Москва", "Маяковская", "10", "2"),
            imageUrls
        )
    )

    private val services: List<Service> = listOf(
        Service(
            serviceId = 123,
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


    fun getMaster(id: String): Master = listMasters.first { it.token == id }
    fun getMaster(): Master = listMasters.first()
    fun getMastersList(): List<Master> = listMasters
    fun getService(serviceId: Long): Service = services.find { it.serviceId == serviceId }!!
    fun getRecordsItemList(): MutableList<RecordItem> = recordsItemList
    fun getCategories(): List<Category> = categories
    fun getServices(): List<Service> = services
}