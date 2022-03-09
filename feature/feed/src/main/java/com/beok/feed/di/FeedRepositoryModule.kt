package com.beok.feed.di

import com.beok.feed.data.FeedRepositoryImpl
import com.beok.feed.domain.repository.FeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface FeedRepositoryModule {

    @Binds
    @Singleton
    fun bindsFeedRepository(impl: FeedRepositoryImpl): FeedRepository
}
