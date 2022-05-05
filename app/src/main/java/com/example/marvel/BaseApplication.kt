package com.example.marvel

import android.app.Application
import com.example.marvel.di.AppModule
import com.example.marvel.di.AppComponent
import com.example.marvel.di.DaggerAppComponent

class BaseApplication : Application() {

    companion object {
        @JvmStatic
        private lateinit var appComponent: AppComponent
        fun component(): AppComponent {
            return appComponent
        }

        @JvmStatic
        private lateinit var appModule: AppModule
        fun module(): AppModule {
            return appModule
        }
    }

    private val component: AppComponent by lazy {
        appModule = AppModule(this)
        DaggerAppComponent.builder()
            .application(appModule)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        appComponent = component
    }
}