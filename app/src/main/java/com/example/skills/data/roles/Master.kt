package com.example.skills.data.roles

import android.net.Uri

data class Master(
    // default
    override val token: String,
    override var email: String,
    override var firstName: String,
    override var lastName: String,
    override var phone: String,
    override var imageId: String? = null,
    override var password: String,
    override var role: Role = Role.MASTER,

    // extended
    var description: String? = null,
    var linkCode: String? = null,
    var address: Address? = null,
    var images: List<Uri>? = null,
) : User(token, email, firstName, lastName, phone, imageId, password, role)


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