package com.example.data.entity

import android.util.Log
import com.example.data.datasource.CharacterRepository
import com.example.data.mapper.CharactersListResponse
import com.example.domain.BuildConfig
import com.example.domain.model.Characters
import com.google.gson.Gson
import com.initishbhatt.marvelsuperheros.api.model.AllCharactersModel
import com.initishbhatt.marvelsuperheros.api.model.DataWrapper
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.math.BigInteger
import java.security.MessageDigest

class CharacterRestRepository(private val characterListApi: CharacterListApi) : CharacterRepository {

    override fun getCharacters(): Single<List<AllCharactersModel>> {
        // Query the service
        val timeStamp = System.currentTimeMillis()
        return getCharacters(timeStamp)
//        return characterListApi.getCharacters()
            .subscribeOn(Schedulers.io())
//            .subscribeBy(onSuccess = {
//                onHerosListReceived(it)
//            }, onError = {
//                onHerosListReceivedError(it)
//            })
            .map {
                val output = Gson().toJson(it)
                Log.e("output", output)
                // if there was an error, throw an exception
                if (it.code != 200) {
                    throw RuntimeException(it.status)
                }

                // Return the list of characters
                return@map it.data.results
            }
    }

//    private fun onHerosListReceivedError(it: Throwable) {
//        isLoading.set(false)
//        herosData.postValue(null)
//        Timber.e(it)
//    }
//
//    private fun onHerosListReceived(it: List<MarvelSuperHeroes>) {
//        isLoading.set(false)
//        herosData.postValue(it)
//    }

//    private fun getCharacters(timeStamp: Long): Single<CharactersListResponse> {
    private fun getCharacters(timeStamp: Long): Single<DataWrapper<List<AllCharactersModel>>> {
        return characterListApi.getCharacters(
            BuildConfig.PUBLIC_KEY,
            hashGenerator(
                "" + timeStamp +
                        BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY
            ),
            timeStamp, 0
        )
    }

    private fun hashGenerator(message: String): String {
        return try {
            val messageDigest = MessageDigest.getInstance("MD5")
            val bytes = messageDigest.digest(message.toByteArray())
            val bigInteger = BigInteger(1, bytes)
            var md5 = bigInteger.toString(16)
            while (md5.length < 32) {
                md5 = "0$md5"
            }
            md5
        } catch (e: Throwable) {
            Log.e("HashGenerator", "Error hashing required parameters:${e.message}")
            ""
        }
    }
}
