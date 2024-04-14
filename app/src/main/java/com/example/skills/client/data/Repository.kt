package com.example.skills.client.data

import com.example.skills.master.data.Master
import java.time.LocalDate
import java.util.Date

class Repository {
    companion object {
        val masters = listOf(
            Master("Kate", null , null, "kate_nail@gmail.com", "79423982394", "Ekaterinburg", "password"),
            Master("Mike", null, null, "mike_build@gmail.com", "79812942368", "Ekaterinburg", "password"),
            Master("Vlad", null, null, "vlad_hair@gmail.com", "79503827560", "Serov", "password"),
            Master("Kris", null, null, "kris_cake@gmail.com", "79222054871", "Ekaterinburg", "password"),

            )
        val currentUser: User = Login()

        private fun Login(): User {
            val newUser = User(
                "Polina",
                "ankpol@gmail.com",
                "9923383811",
                "Serov",
                Date(2004, 4, 17),
                "fwofnsofns",
                listOf(Appointment(LocalDate.now(), "123" + "Kris"  ))
            )
            return newUser
        }

        fun getMaster(id: String): Master? {
            return masters.find { it.id == id }
        }
    }
}