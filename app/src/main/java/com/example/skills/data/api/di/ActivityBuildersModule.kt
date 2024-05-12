package com.example.skills.data.api.di


import com.example.skills.data.api.di.auth.AuthFragmentBuildersModule
import com.example.skills.data.api.di.auth.AuthModule
import com.example.skills.data.api.di.auth.AuthScope
import com.example.skills.data.api.di.auth.AuthViewModelModule
import com.example.skills.general.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
        modules = [AuthModule::class, AuthFragmentBuildersModule::class, AuthViewModelModule::class]
    )
    abstract fun contributeAuthActivity(): MainActivity //change that

}