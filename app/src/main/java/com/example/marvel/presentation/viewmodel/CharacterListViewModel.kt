package com.example.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Characters

class CharacterListViewModel: ViewModel() {

    private val characterLiveData = MutableLiveData<List<Characters>>()
    private val loadingLiveData = MutableLiveData<Boolean>()

    fun setCharacters(characterLiveData: List<Characters>) {
        this.characterLiveData.value = characterLiveData
    }

    fun getCharacters(): LiveData<List<Characters>> {
        return characterLiveData
    }

    fun setLoading(loadingLiveData: Boolean) {
        this.loadingLiveData.value = loadingLiveData
    }

    fun isLoading(): LiveData<Boolean> {
        return loadingLiveData
    }
}