package com.example.skills.data.api

import com.example.skills.data.entity.Address
import com.example.skills.data.entity.Duration
import com.example.skills.data.entity.TimeSlot
import com.example.skills.data.roles.User
import com.example.skills.data.roles.UserRequest
import java.io.File
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
    val masterId: Int? = null,
    val address: AddressResponse? = null,
    val messenger: String? = null,
    val profilePictureId: Int? = null,
    val additionalImagesIds: List<Int>? = null,
    val phoneNumber: String? = null,
    val services: List<ServiceResponse>? = null,
    val categories: List<CategoryResponse>? = null,
    val schedule: List<ScheduleResponse>? = null
)

data class MasterForClient(
    val id: Int,
    var fullName: String? = null,
    var description: String? = null,
    var address: AddressResponse? = null,
    var masterId: Int? = null,
    var messenger: String? = null,
    var profilePictureId: Int? = null,
    var additionalImagesIds: List<Int>? = null,
    var phoneNumber: String? = null,
    var services: List<ServiceResponse>? = null,
    var categories: List<CategoryResponse>? = null,
    var schedule: List<ScheduleResponse>? = null,

    var profileImage: File? = null,
    var images: MutableList<File>? = null
)

data class ServiceResponse(
    val name: String? = null,
    val price: Double? = null,
    val duration: Duration? = null
)

data class CategoryResponse(
    val id: Int? = null,
    val name: String? = null
)

data class AddressResponse(
    val street: String? = null,
    var city: String? = null,
    val state: String? = null,
    val zipCode: String? = null,
    val country: String? = null
)

data class ScheduleResponse(
    val dayOfWeek: String? = null,
    val startTime: String? = null,
    val endTime: String? = null
)

data class StartTimeSlot(
    val hour: Int,
    val minute: Int,
    val second: Int,
    val nano: Int
)

data class BookingRequest(
    val date: String,
    val timeFrom: String,
    val serviceId: Int,
    val comment: String
)