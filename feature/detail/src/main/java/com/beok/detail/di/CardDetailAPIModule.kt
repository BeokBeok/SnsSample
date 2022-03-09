package com.beok.detail.di

import com.beok.detail.data.remote.CardDetailAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object CardDetailAPIModule {

    @Provides
    @Singleton
    fun providesCardDetailAPI(retrofit: Retrofit): CardDetailAPI =
        retrofit.create(CardDetailAPI::class.java)
}
