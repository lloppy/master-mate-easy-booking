package com.example.skills.data.models

data class Category(
//    val id: Long,
    val name: String,
    val description: String? = null,
    var action: () -> Unit
)