package com.example.skills.client.data

import java.util.Date

data class User(
    var name: String,
    var email: String,
    var phone: String,
    var city: String,
    var birthdate: Date,
    var password: String
)