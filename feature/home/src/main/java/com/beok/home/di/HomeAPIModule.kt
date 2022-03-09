package com.beok.home.di

import com.beok.home.data.remote.HomeAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object HomeAPIModule {

    @Provides
    @Singleton
    fun providesHomeAPI(retrofit: Retrofit): HomeAPI =
        retrofit.create(HomeAPI::class.java)
}
