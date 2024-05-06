package com.example.skills.data

import android.net.Uri
import com.example.skills.data.models.Service

data class Master(
    // default
    override val id: Long,
    override var email: String,
    override var firstName: String,
    override var lastName: String,
    override var phone: String,
    override var imageId: String?,
    override var password: String,
    override var role: Role = Role.MASTER,

    // extended
    var description: String,
    var linkCode: String,
    var address: String,
    var images: List<Uri>,
    var services: List<Service> = emptyList()
) : User(id, email, firstName, lastName, phone, imageId, password, role)
