package com.example.skills.master.data

import android.net.Uri

data class Master(
    var name: String,
    val profileDescription: String? = null,
    var photo: Uri? = null,
    var email: String,
    var phone: String,
    var city: String,
    var password: String,
    val id: String = "123" + name
)