package com.example.skills.data.entity

data class Schedule(
    val date: String,
    val timeSlots: List<TimeSlot>
)

data class TimeSlot(
    val from: String,
    val to: String
)
