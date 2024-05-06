package com.example.skills.data.models

import com.example.skills.data.Master

data class Service(
    val serviceId: Long,
    val master: Master,
    var name: String,
    var description: String,
    var price: Int,
    var duration: Int,
    var category: Category
)
