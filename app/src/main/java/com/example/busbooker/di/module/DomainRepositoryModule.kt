package com.example.busbooker.di.module

import com.example.data.repository.BusRoutesManagerImpl
import com.example.data.repository.SignInSignUpManagerImpl
import com.example.domain.repository.BusRoutesManager
import com.example.domain.repository.SignInSignUpManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainRepositoryModule {

    @Singleton
    @Provides
    fun provideSignInSignUpManager(): SignInSignUpManager {
        return SignInSignUpManagerImpl()
    }

    @Singleton
    @Provides
    fun provideBusRoutesManager(): BusRoutesManager {
        return BusRoutesManagerImpl()
    }

}