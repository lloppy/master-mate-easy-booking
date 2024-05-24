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
    var address: Address,
    var images: List<Uri>,
) : User(id, email, firstName, lastName, phone, imageId, password, role)


data class Address(
    val country: String,
    val city: String,
    val street: String,
    val house: String,
    val office: String
) {
    override fun toString(): String {
        return "Страна: $country, г.$city, ул.$street, д.$house - $office"
    }
}