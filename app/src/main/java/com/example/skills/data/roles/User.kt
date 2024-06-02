package com.example.skills.data.roles

import android.net.Uri
import com.example.skills.data.entity.Address

class User(
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
        var birthday: String?
    )

    class Master(
        var profileImageId: String? = null,
        var images: List<Uri>? = null,

        var description: String? = null,
        var linkCode: String? = null,
        var messenger: String? = null,
        var address: Address? = null,
    )
}

class UserRequest(
    var firstName: String,
    var lastName: String,
    var phone: String
)