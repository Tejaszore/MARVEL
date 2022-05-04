package com.matthiasbruns.kotlintutorial.dog.networking

import retrofit2.Call
import retrofit2.http.GET

interface CharacterListApi {

    @GET("/v1/public/characters")
    fun getCharacters(): Call<MutableList<CharactersListResponse>>
}