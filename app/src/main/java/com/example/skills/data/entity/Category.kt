package com.example.skills.data.entity

data class Category(
    val name: String,
    val description: String,
    var action: () -> Unit = {}
)