package com.example.data.entity

import com.example.data.repositoryimpl.CharacterRepository
import com.example.domain.repository.CharacterRestRepository
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



