package com.example.skills.data.api.repository.auth

import com.example.skills.data.api.auth.ApiAuthService
import com.example.skills.data.api.persistence.AccountPropertiesDao
import com.example.skills.data.api.persistence.AuthTokenDao
import com.example.skills.data.api.session.SessionManager

class AuthRepository
constructor(
    val authTokenDao: AuthTokenDao,
    accountPropertiesDao: AccountPropertiesDao,
    apiAuthService: ApiAuthService,
    sessionManager: SessionManager
)
{

}