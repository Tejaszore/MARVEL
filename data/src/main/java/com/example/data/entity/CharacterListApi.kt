package com.example.data.entity

import com.example.data.mapper.CharactersListResponse
import com.example.domain.model.Characters
import com.initishbhatt.marvelsuperheros.api.model.AllCharactersModel
import com.initishbhatt.marvelsuperheros.api.model.DataWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

const val HASH = "hash"
const val APIKEY = "apikey"
const val TIMESTAMP = "ts"
const val OFFSET = "offset"
const val LIMIT = "limit"

interface CharacterListApi {

//    @GET("/v1/public/characters")
//    fun getCharacters(): Call<MutableList<CharactersListResponse>>

//    @GET("/v1/public/characters")
//    fun getCharacters(): Single<CharactersListResponse>

    @GET("characters")
    fun getCharacters(
        @Query(APIKEY) apikey: String,
        @Query(HASH) md5Digest: String,
        @Query(TIMESTAMP) timestamp: Long,
//                      @Query(OFFSET) offset: Int?): Single<CharactersListResponse<List<Characters>>>
//                      @Query(OFFSET) offset: Int?): Single<CharactersListResponse>
//                      @Query(OFFSET) offset: Int?): Single<DataWrapper<List<Characters>>>
        @Query(OFFSET) offset: Int?
    ): Single<DataWrapper<List<AllCharactersModel>>>
}