package com.example.marvel.di

import com.example.marvel.presentation.PresenterConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresentationModule {

    @Provides
    @Singleton
    fun providePresenterConfig(): PresenterConfig {
        return PresenterConfig()
    }
}