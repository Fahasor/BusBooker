package com.example.busbooker.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.busbooker.di.module.AppCiceroneModule
import com.example.busbooker.di.module.AppModule
import com.example.busbooker.di.module.AppViewModelModule
import com.example.busbooker.di.module.DomainRepositoryModule
import com.example.busbooker.di.module.DomainUseCaseModule
import com.example.busbooker.presentation.adapter.RvItemsAdapter
import com.example.busbooker.presentation.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =
[AppCiceroneModule::class,
    AppViewModelModule::class,
    AppModule::class,
    DomainUseCaseModule::class,
    DomainRepositoryModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationContext(context: Context): Builder
        fun build(): AppComponent
    }

    fun provideFactory(): ViewModelProvider.Factory

    fun inject(mainActivity: MainActivity)

    fun inject(categoryHolder: RvItemsAdapter.CategoryHolder)

}