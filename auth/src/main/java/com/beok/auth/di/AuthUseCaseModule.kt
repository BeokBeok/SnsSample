package com.beok.auth.di

import com.beok.auth.domain.usecase.CheckSignInUseCase
import com.beok.auth.domain.usecase.CheckSignInUseCaseImpl
import com.beok.auth.domain.usecase.SignInUseCase
import com.beok.auth.domain.usecase.SignInUseCaseImpl
import com.beok.auth.domain.usecase.SignOutUseCase
import com.beok.auth.domain.usecase.SignOutUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface AuthUseCaseModule {

    @Binds
    @Singleton
    fun bindsSignInUseCase(impl: SignInUseCaseImpl): SignInUseCase

    @Binds
    @Singleton
    fun bindsCheckSignInUseCase(impl: CheckSignInUseCaseImpl): CheckSignInUseCase

    @Binds
    @Singleton
    fun bindsSignOutUseCase(impl: SignOutUseCaseImpl): SignOutUseCase
}
