package com.example.skills.general.auth

import androidx.lifecycle.ViewModel
import com.example.skills.data.api.repository.auth.AuthRepository

class AuthViewModel
constructor(
    val authRepository: AuthRepository
) : ViewModel()
{

}