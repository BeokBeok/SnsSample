package com.beok.detail.di

import com.beok.detail.data.CardDetailRepositoryImpl
import com.beok.detail.domain.repository.CardDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface CardDetailRepositoryModule {

    @Binds
    @Singleton
    fun bindsCardDetailRepository(impl: CardDetailRepositoryImpl): CardDetailRepository
}
