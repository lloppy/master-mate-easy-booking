package com.example.skills.data.api

import com.example.skills.data.entity.Address
import com.example.skills.data.entity.TimeSlot
import com.example.skills.data.roles.User
import com.example.skills.data.roles.UserRequest
import java.time.LocalDateTime

data class AuthRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val birthDate: String? = null
)

data class LogInRequest(
    val email: String,
    val password: String,
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
    val user: UserRequest,
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

data class ScheduleChangeRequest(
    val dates: List<String>,
    val timeSlots: List<TimeSlot>
)

data class ScheduleCreateRequest(
    val dates: List<String>,
    val timeSlots: List<TimeSlot>
)