package com.beok.auth.di

import com.beok.auth.data.remote.AuthAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object AuthAPIModule {

    @Provides
    @Singleton
    fun providesAuthAPI(retrofit: Retrofit): AuthAPI = retrofit.create(AuthAPI::class.java)
}
