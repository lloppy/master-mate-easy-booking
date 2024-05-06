package com.example.skills.data.models

import com.example.skills.data.Client
import com.example.skills.data.Master
import java.time.LocalDate
import java.time.LocalTime

data class RecordItem(
    val id: Long,
    val timeFrom: LocalDate,
    val timeTo: LocalTime,
    val master: Master,
    val client: Client,
    val comment: String,
    val service: Service,
    val recordStatus: RecordStatus
)

enum class RecordStatus { ACTUAL, ARCHIVE }