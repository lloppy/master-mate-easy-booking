package com.example.skills.data.api.di.auth

import androidx.lifecycle.ViewModel
import com.example.skills.data.api.di.ViewModelKey
import com.example.skills.general.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel

}