package com.example.skills.data.models

import com.example.skills.data.Client
import com.example.skills.data.Master
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class RecordItem(
// // val id: Long,
//    val timeFrom: LocalDate,
//    val timeTo: LocalTime,
// //   val master: Master,
//    val client: Client,
//    val comment: String,
//    val service: Service,
//    val recordStatus: RecordStatus


    val serviceName: String,
    val price: Int,
    val timeFrom: LocalDateTime,
    val duration: Int,
    val clientName: String,
    val clientAge: Int,
    val recordStatus: RecordStatus,
    val isDone: Boolean? = if (recordStatus == RecordStatus.ACTUAL) null else false,
    val comment: String? = null,
    val clientId: Long? = null,
    val masterId: Long = 123,
    val serviceId: Long = 123
)

enum class RecordStatus { ACTUAL, ARCHIVE }