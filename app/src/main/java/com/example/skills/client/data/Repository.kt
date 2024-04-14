package com.example.skills.client.data

import java.util.Date

class Repository {

    companion object {
        val currentUser: User = Login()

        private fun Login(): User {
            val newUser = User(
                "Polina",
                "ankpol@gmail.com",
                "9923383811",
                "Serov",
                Date(2004, 4, 17),
                "fwofnsofns"
            )
            return newUser
        }
    }
}