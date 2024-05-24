package com.example.skills.data.entity

import com.example.skills.data.roles.Master

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