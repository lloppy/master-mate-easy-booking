package com.example.skills.data.api.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.skills.data.models.AccountProperties
import com.example.skills.data.models.AuthToken

@Database(entities = [AuthToken::class, AccountProperties::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getAuthTokenDao(): AuthTokenDao
    abstract fun getAccountPropertiesDao(): AccountPropertiesDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }

}