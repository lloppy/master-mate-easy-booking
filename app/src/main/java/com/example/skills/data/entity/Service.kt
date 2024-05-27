package com.example.skills.data.entity

import com.example.skills.data.roles.User

data class Service(
    val serviceId: Long,
    val master: User,
    val name: String,
    val description: String?,
    val price: Long?,
    val duration: Long,
   // val image: Image?,
    val category: Category,
   // val serviceStatus: ServiceStatus
)