package com.example.skills.data.models

import java.time.LocalDateTime

data class RecordItem(
    val serviceName: String,
    val price: Int,
    val timeStart: LocalDateTime,
    val duration: Int,
    val clientName: String,
    val clientAge: Int,
    val status: Status,
    val isDone: Boolean? = if (status == Status.ACTUAL) null else false,
    val comment: String? = null,
    val clientId: Long? = null,
    val masterId: Long = 123,
    val serviceId: Long = 123
)

enum class Status { ACTUAL, ARCHIVE }