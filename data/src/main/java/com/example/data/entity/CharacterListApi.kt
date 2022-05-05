package com.example.data.entity

import com.example.data.mapper.CharactersListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CharacterListApi {

//    @GET("/v1/public/characters")
//    fun getCharacters(): Call<MutableList<CharactersListResponse>>

    @GET("/v1/public/characters")
    fun getCharacters(): Single<CharactersListResponse>
}