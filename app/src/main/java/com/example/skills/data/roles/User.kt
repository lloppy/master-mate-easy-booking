package com.example.skills.data.roles

import android.graphics.Bitmap
import com.example.skills.data.api.AddressResponse
import com.example.skills.data.api.CategoryResponse
import com.example.skills.data.api.ScheduleResponse
import com.example.skills.data.api.ServiceResponse
import com.example.skills.data.entity.Address
import java.io.File

class User(
    var id: Int? = null,
    val token: String,
    var email: String,
    var password: String,

    var firstName: String,
    var lastName: String,
    var phone: String,
    var role: Role,

    var master: Master? = null,
    var client: Client? = null

) {
    class Client(
        val mastersUserIds: List<Int> = emptyList(),
        var birthday: String?
    )

    class Master(
        var id: Int? = null,
        var profileImage: Bitmap? = null,
        var images: MutableList<Bitmap>? = null,

        var description: String? = null,
        var linkCode: String? = null,
        var personalCode: String? = null,
        var address: Address? = null
    )
}

class UserRequest(
    var firstName: String,
    var lastName: String,
    var phone: String
)

class ClientRequest(
    var firstName: String,
    var lastName: String,
    val email: String,
    var phone: String
)

data class UserTokenResponse(
    val id: Int,
    val email: String = "",
    val emailToChange: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phone: String = "",
    val roles: List<String> = listOf(),
    val master: Master? = null,
    val client: Client? = null
) {
    data class Master(
        val masterId: Int,
        val fullName: String = "",
        val description: String = "",
        val address: AddressResponse,
        val messenger: String = "",
        val profilePictureId: Int,
        val additionalImagesIds: List<Int> = listOf(),
        val phoneNumber: String = "",
        val services: List<ServiceResponse> = listOf(),
        val categories: List<CategoryResponse> = listOf(),
        val schedule: List<ScheduleResponse> = listOf()
    )

    data class Client(
        val id: Int,
        val mastersIds: List<Int> = listOf(),
        val birthDate: String = ""
    )
}