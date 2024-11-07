package com.example.busbooker.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.busbooker.di.ViewModelFactory
import com.example.busbooker.di.ViewModelKey
import com.example.busbooker.presentation.viewmodel.MyRoutesViewModel
import com.example.busbooker.presentation.viewmodel.DetailsViewModel
import com.example.busbooker.presentation.viewmodel.HomeViewModel
import com.example.busbooker.presentation.viewmodel.MainViewModel
import com.example.busbooker.presentation.viewmodel.SignInViewModel
import com.example.busbooker.presentation.viewmodel.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AppViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun homeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    fun signInViewModel(viewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun signUpViewModel(viewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun detailsViewModel(viewModel: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyRoutesViewModel::class)
    fun myRoutesViewModel(viewModel: MyRoutesViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}