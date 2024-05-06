package com.example.skills.data.models

import com.example.skills.data.Master
import java.time.LocalDate

data class Service(
    val serviceId: Long,
    val master: Master,
    val name: String,
    val description: String?,
    val price: Long?,
    val duration: Long,
   // val image: Image?,
    val category: Category,
   // val serviceStatus: ServiceStatus
)

//enum class ServiceStatus {
//    STATUS1, STATUS2, STATUS3
//}