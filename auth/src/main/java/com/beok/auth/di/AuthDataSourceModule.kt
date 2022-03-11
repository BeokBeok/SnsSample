package com.beok.auth.di

import com.beok.auth.data.local.AuthLocalDataSource
import com.beok.auth.data.local.AuthLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface AuthDataSourceModule {

    @Binds
    @Singleton
    fun bindsAuthLocalDataSource(impl: AuthLocalDataSourceImpl): AuthLocalDataSource
}
