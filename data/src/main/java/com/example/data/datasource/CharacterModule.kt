package com.example.data.datasource

import com.example.data.entity.CharacterRestRepository
import com.matthiasbruns.kotlintutorial.dog.networking.CharacterListApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class CharacterModule {

    @Provides
    @Singleton
    fun provideDogsRepository(retrofit: Retrofit): CharacterRepository {
        // You can decide by whatever params which repo you want to inject
        return CharacterRestRepository(retrofit.create(CharacterListApi::class.java))
    }
}



