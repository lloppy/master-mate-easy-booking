package com.example.skills.data.api.session

import android.app.Application
import com.example.skills.data.api.persistence.AuthTokenDao

class SessionManager
constructor(
    val authTokenDao: AuthTokenDao,
    val application: Application
)
{

}