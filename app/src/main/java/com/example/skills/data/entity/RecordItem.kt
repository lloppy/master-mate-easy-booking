package com.example.skills.data.entity

import com.example.skills.data.roles.User

data class RecordItem(
    val id: Int,
    val masterId: Int = 1,
    val date: String,
    val status: String,
    val comment: String = "some comment",
    val timeFrom: String,
    val timeTo: String,
    val service: Service,
    val client: ClientResponse
)

enum class RecordStatus {
    CREATED, CANCELLED, COMPLETED, IN_PROGRESS
}
data class ClientResponse(
    val fullName: String,
    val age: Int,
    val phone: String
)