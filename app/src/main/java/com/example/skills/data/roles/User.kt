package com.example.skills.data.roles

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
        val mastersId : List<Int> = emptyList(),
        var birthday: String?
    )

    class Master(
        var profileImage: File? = null,
        var images: MutableList<File>? = null,

        var description: String? = null,
        var linkCode: String? = null,
        var personalCode: String? = null,
        var address: Address? = null,
    )
}

class UserRequest(
    var firstName: String,
    var lastName: String,
    var phone: String
)
