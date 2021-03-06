package com.example.data.datasource

import com.example.domain.model.Characters
import com.initishbhatt.marvelsuperheros.api.model.AllCharactersModel
import io.reactivex.Single

interface CharacterRepository {

//    fun getCharacters(): List<Characters>
//    fun getCharacters(): Single<List<Characters>>
    fun getCharacters(): Single<List<AllCharactersModel>>
}