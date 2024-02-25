package com.example.miappcurso.data.login.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DatabaseUsuarioEntity {
        return Room.databaseBuilder(
            context,
            DatabaseUsuarioEntity::class.java,
            "MY_DATABASE_USUARIO"
        ).build()
    }
}