package com.example.skills.data.api

import com.example.skills.data.roles.Address
import com.example.skills.data.roles.User
import java.time.LocalDateTime

data class AuthRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val birthDate: String? = null
)


data class AuthResponse(
    val token: String,
    val error: String?
)

data class ActivationRequest(
    val code: String
)

data class ActivationResponse(
    val status: String
)

data class EditMasterRequest(
    val user: User,
    val description: String,
    val address: Address,
    val linkCode: String
)

data class EditMasterResponse(
    val message: String,
    val status: String
)

data class EditClientRequest(
    val user: User,
    val birthDate: LocalDateTime
)

data class EditClientResponse(
    val message: String,
    val status: String
)