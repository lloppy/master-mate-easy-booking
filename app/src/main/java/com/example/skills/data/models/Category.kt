package com.example.skills.data.models

data class Category(
    val name: String,
    var action: () -> Unit
)