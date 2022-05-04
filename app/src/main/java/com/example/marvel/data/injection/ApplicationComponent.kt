package com.example.marvel.data.injection

import android.app.Application
import com.example.marvel.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ForApplication
@Component(modules = [AppModule::class])
interface ApplicationComponent {

    fun inject(application: BaseApplication)
}