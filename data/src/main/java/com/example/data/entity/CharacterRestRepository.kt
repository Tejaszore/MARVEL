package com.example.data.entity

import com.example.data.datasource.CharacterRepository
import com.example.domain.model.Characters
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CharacterRestRepository(private val characterListApi: CharacterListApi) : CharacterRepository {

    override fun getCharacters(): Single<List<Characters>> {
        // Query the service
        return characterListApi.getCharacters()
                .subscribeOn(Schedulers.io())
                .map {
                    // if there was an error, throw an exception
                    if (it.error != null) {
                        throw RuntimeException(it.error)
                    }

                    // Return the list of characters
                    return@map it.data
                }
    }
}
