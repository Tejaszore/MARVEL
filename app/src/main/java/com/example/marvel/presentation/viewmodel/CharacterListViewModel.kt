package com.example.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Characters
import com.initishbhatt.marvelsuperheros.api.model.AllCharactersModel

class CharacterListViewModel: ViewModel() {

    private val characterLiveData = MutableLiveData<List<AllCharactersModel>>()
    private val loadingLiveData = MutableLiveData<Boolean>()

    fun setCharacters(characterLiveData: List<AllCharactersModel>) {
        this.characterLiveData.value = characterLiveData
    }

    fun getCharacters(): LiveData<List<AllCharactersModel>> {
        return characterLiveData
    }

    fun setLoading(loadingLiveData: Boolean) {
        this.loadingLiveData.value = loadingLiveData
    }

    fun isLoading(): LiveData<Boolean> {
        return loadingLiveData
    }
}