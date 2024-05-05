package com.example.skills.data

import android.net.Uri
import com.example.skills.master.components.d.Category

data class Master(
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
    var description: String,
    var linkCode: String,
    var address: String,
    var images: List<Uri>,
    var categories: List<Category> = emptyList()
) : User(id, email, firstName, lastName, phone, imageId, password, role)
