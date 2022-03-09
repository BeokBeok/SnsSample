package com.beok.auth.di

import com.beok.auth.data.AuthRepositoryImpl
import com.beok.auth.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface AuthRepositoryModule {

    @Binds
    @Singleton
    fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}
