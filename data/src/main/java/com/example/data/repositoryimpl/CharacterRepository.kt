package com.example.data.repositoryimpl

import com.example.domain.model.Characters

interface CharacterRepository {

    fun getCharacters(): List<Characters>
//    fun getCharacters(): Single<List<Characters>>
}