package com.example.skills.data.roles

import java.time.LocalDate

data class Client(
    // default
    override val id: Long,
    override var email: String,
    override var firstName: String,
    override var lastName: String,
    override var phone: String,
    override var imageId: String?,
    override var password: String,
    override var role: Role = Role.CLIENT,

    // extended
    var dateBirthday: LocalDate
) : User(id, email, firstName, lastName, phone, imageId, password, role)
