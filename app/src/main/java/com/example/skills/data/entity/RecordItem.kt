package com.example.skills.data.entity

import java.time.LocalDateTime

data class RecordItem(
    val serviceName: String,
    val price: Int,
    val timeFrom: LocalDateTime,
    val duration: Int,
    val clientName: String,
    val clientAge: Int,
    val recordStatus: RecordStatus,
    val isDone: Boolean? = if (recordStatus == RecordStatus.ACTUAL) null else false,
    val comment: String? = null,
    val clientId: String? = null,
    val masterId: String = "123",
    val serviceId: Int = 123
)

enum class RecordStatus { ACTUAL, ARCHIVE }