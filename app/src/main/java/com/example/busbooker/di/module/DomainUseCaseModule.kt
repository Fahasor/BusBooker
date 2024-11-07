package com.example.busbooker.di.module

import com.example.domain.repository.SignInSignUpManager
import com.example.domain.usecase.SignInUseCase
import com.example.domain.usecase.SignUpUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainUseCaseModule {

    @Provides
    fun provideSignUpUseCase(signInSignUpManager: SignInSignUpManager): SignUpUseCase {
        return SignUpUseCase(signInSignUpManager)
    }

    @Provides
    fun provideSignInUseCase(signInSignUpManager: SignInSignUpManager): SignInUseCase {
        return SignInUseCase(signInSignUpManager)
    }

}