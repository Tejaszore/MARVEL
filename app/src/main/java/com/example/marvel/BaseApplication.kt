package com.example.marvel

import android.app.Application
import com.example.marvel.di.AppModule
import com.example.marvel.di.ApplicationComponent
import com.example.marvel.di.DaggerApplicationComponent

class BaseApplication : Application() {

companion object {
    @JvmStatic
    private lateinit var appComponent: ApplicationComponent
    fun component(): ApplicationComponent {
        return appComponent
    }

    @JvmStatic
    private lateinit var appModule: AppModule
    fun module(): AppModule {
        return appModule
    }
}
    private val component: ApplicationComponent by lazy {
        appModule = AppModule(this)

        DaggerApplicationComponent.builder()
            .appModule(appModule)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        appComponent = component
    }

}