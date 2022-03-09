package com.beok.feed.di

import com.beok.feed.data.remote.FeedAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object FeedAPIModule {

    @Provides
    @Singleton
    fun providesFeedAPI(retrofit: Retrofit): FeedAPI =
        retrofit.create(FeedAPI::class.java)
}
