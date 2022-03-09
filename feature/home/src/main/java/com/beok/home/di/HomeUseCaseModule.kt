package com.beok.home.di

import com.beok.home.domain.usecase.FetchHomeUseCase
import com.beok.home.domain.usecase.FetchHomeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface HomeUseCaseModule {

    @Binds
    @Singleton
    fun bindsFetchHomeUseCase(impl: FetchHomeUseCaseImpl): FetchHomeUseCase
}
