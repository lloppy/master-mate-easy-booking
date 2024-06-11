package com.example.skills.data.entity

data class RecordItem(
    val id: Int,
    val masterId: Int,
    val date: String,
    val status: String,
    val timeFrom: String,
    val timeTo: String,
    val service: Service,
    val client: ClientResponse,
    val comment: String
)

enum class RecordStatus {
    CREATED, CANCELLED, COMPLETED, IN_PROGRESS
}

data class ClientResponse(
    val fullName: String,
    val age: Int,
    val phone: String
)