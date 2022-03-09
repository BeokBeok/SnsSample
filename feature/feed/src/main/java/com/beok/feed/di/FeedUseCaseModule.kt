package com.beok.feed.di

import com.beok.feed.domain.usecase.FetchFeedUseCase
import com.beok.feed.domain.usecase.FetchFeedUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface FeedUseCaseModule {

    @Binds
    @Singleton
    fun bindsFetchFeedUseCase(impl: FetchFeedUseCaseImpl): FetchFeedUseCase
}
