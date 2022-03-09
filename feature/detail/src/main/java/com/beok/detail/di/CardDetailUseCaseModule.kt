package com.beok.detail.di

import com.beok.detail.domain.usecase.FetchCardDetailUseCase
import com.beok.detail.domain.usecase.FetchCardDetailUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface CardDetailUseCaseModule {

    @Binds
    @Singleton
    fun bindsFetchCardDetailUseCase(impl: FetchCardDetailUseCaseImpl): FetchCardDetailUseCase
}
