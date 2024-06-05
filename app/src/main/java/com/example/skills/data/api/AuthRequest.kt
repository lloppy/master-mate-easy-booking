package com.example.skills.data.api

import com.example.skills.data.entity.Address
import com.example.skills.data.entity.Category
import com.example.skills.data.entity.Duration
import com.example.skills.data.entity.Schedule
import com.example.skills.data.entity.Service
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

data class MasterForClientResponse(
    val id: Int,
    val fullName: String? = null,
    val description: String? = null,
    val address: AddressResponse? = null,
    val messenger: String? = null,
    val profilePictureId: Int? = null,
    val additionalImagesIds: List<Int>? = null,
    val phoneNumber: String? = null,
    val services: List<ServiceResponse>? = null,
    val categories: List<CategoryResponse>? = null,
    val schedule: List<ScheduleResponse>? = null
)

data class ServiceResponse(
    val name: String? = null,
    val price: Double? = null,
    val duration: Int? = null
)

data class CategoryResponse(
    val id: Int? = null,
    val name: String? = null
)

data class AddressResponse(
    val street: String? = null,
    val city: String? = null,
    val state: String? = null,
    val zipCode: String? = null,
    val country: String? = null
)

data class ScheduleResponse(
    val dayOfWeek: String? = null,
    val startTime: String? = null,
    val endTime: String? = null
)