package com.example.skills.client.data

import android.util.Log
import com.example.skills.master.data.Master
import java.time.LocalDate

fun findMasterById(id: String): Master? {
    val master = Repository.getMaster(id = id)
    if (master != null) {
        return master
    } else {
        Log.w("Appointment", "Warning! findMasterById: master is null")
        return null
    }
}

data class Appointment(
    var dateAndTime: LocalDate,
    val masterDestinationId: String,
    val masterDestination: Master? = findMasterById(id = masterDestinationId)
)