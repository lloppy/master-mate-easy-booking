package com.example.skills.data.roles

import java.time.LocalDate

data class Client(
    // default
    override val token: String,
    override var email: String,
    override var firstName: String,
    override var lastName: String,
    override var phone: String,
    override var imageId: String? = null,
    override var password: String,
    override var role: Role = Role.CLIENT,

    // extended
    var dateBirthday: LocalDate
) : User(token, email, firstName, lastName, phone, imageId, password, role)
