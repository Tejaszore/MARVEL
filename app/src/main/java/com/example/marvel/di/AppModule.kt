package com.example.marvel.di

import android.content.Context
import com.example.marvel.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: BaseApplication) {

    @Singleton
    @Provides
    fun provideApplication(): BaseApplication = application

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return application
    }
}