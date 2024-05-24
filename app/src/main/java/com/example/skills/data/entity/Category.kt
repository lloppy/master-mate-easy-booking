package com.example.skills.data.entity

data class Category(
//    val id: Long,
    val name: String,
    val description: String? = null,
    var action: () -> Unit
)