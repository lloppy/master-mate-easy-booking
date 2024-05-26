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
    val city: String,
    val country: String? = null,
    val street: String? = null,
    val house: String? = null,
    val office: String? = null,
) {
    override fun toString(): String {
        return city
        // return "Страна: $country, г.$city, ул.$street, д.$house - $office"
    }
}