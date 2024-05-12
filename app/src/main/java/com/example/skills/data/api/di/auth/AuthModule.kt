package com.example.skills.data.api.di.auth

import com.example.skills.data.api.auth.ApiAuthService
import com.example.skills.data.api.persistence.AccountPropertiesDao
import com.example.skills.data.api.persistence.AuthTokenDao
import com.example.skills.data.api.repository.auth.AuthRepository
import com.example.skills.data.api.session.SessionManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule{

    // TEMPORARY
    @AuthScope
    @Provides
    fun provideFakeApiService(): ApiAuthService {
        return Retrofit.Builder()
            .baseUrl("https://open-api.xyz") //change there
            .build()
            .create(ApiAuthService::class.java)
    }

    @AuthScope
    @Provides
    fun provideAuthRepository(
        sessionManager: SessionManager,
        authTokenDao: AuthTokenDao,
        accountPropertiesDao: AccountPropertiesDao,
        openApiAuthService: ApiAuthService
    ): AuthRepository {
        return AuthRepository(
            authTokenDao,
            accountPropertiesDao,
            openApiAuthService,
            sessionManager
        )
    }

}