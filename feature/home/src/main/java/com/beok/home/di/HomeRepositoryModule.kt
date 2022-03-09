package com.beok.home.di

import com.beok.home.data.HomeRepositoryImpl
import com.beok.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface HomeRepositoryModule {

    @Binds
    @Singleton
    fun bindsHomeRepository(impl: HomeRepositoryImpl): HomeRepository
}
