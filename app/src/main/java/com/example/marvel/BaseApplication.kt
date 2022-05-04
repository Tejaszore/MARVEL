package com.example.marvel

import android.app.Application
import com.example.marvel.data.injection.AppModule
import com.example.marvel.data.injection.ApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

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
//        component.inject(this)
//        appComponent = component
    }

//    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
//        return DaggerApplicationCompnent
//    }
}