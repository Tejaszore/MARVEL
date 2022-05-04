package com.example.domain.repository

import com.example.data.repositoryimpl.CharacterRepository
import com.example.domain.model.Characters
import com.matthiasbruns.kotlintutorial.dog.networking.CharacterListApi
import com.matthiasbruns.kotlintutorial.dog.networking.CharactersListResponse
import io.reactivex.schedulers.Schedulers

class CharacterRestRepository(private val characterListApi: CharacterListApi) : CharacterRepository {

    override fun getCharacters(): List<Characters> {
        // Query the service
        return characterListApi.getCharacters()
                .subscribeOn(Schedulers.io())
                .map { dogsResponse: CharactersListResponse ->
                    // if there was an error, throw an exception
                    if (dogsResponse.error != null) {
                        throw RuntimeException(dogsResponse.error)
                    }

                    // Return the list of dogs
                    return@map dogsResponse.data
                }
    }
}
