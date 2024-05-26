package com.example.skills.data.entity

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