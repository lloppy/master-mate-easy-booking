package com.example.skills.data.entity

data class CategoryRequest(
    val name: String,
    val description: String
)

data class Category(
    val id: Int,
    val name: String,
    val description: String,
    var action: () -> Unit = {}
)

