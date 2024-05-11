package com.example.skills.data.api

data class AuthRequest(
    val email: String,
    val password: String
)



data class AuthResponse(
    val token: String,
    val error: String
)

data class ActivationRequest(
    val code: String
)

data class ActivationResponse(
    val message: String
)