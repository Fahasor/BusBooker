package com.example.busbooker

import android.app.Application
import com.example.busbooker.di.AppComponent
import com.example.busbooker.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy(
        DaggerAppComponent
            .builder()
            .applicationContext(this)::build
    )

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        internal lateinit var instance: App
            private set
    }

}