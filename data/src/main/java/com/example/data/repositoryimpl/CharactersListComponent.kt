package com.example.data.repositoryimpl

import com.example.data.retrofit.NetworkModule
import com.example.marvel.di.AppModule
import com.example.marvel.presentation.activity.MarvelCharacterListActivity
import com.example.marvel.presentation.fragment.CharacterListFragment
import com.example.data.datasource.CharacterModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        CharacterModule::class,
        NetworkModule::class])
interface CharactersListComponent {
    fun inject(activity: MarvelCharacterListActivity)
    fun inject(fragment: CharacterListFragment)
//    fun inject(presenter: DogsListPresenter)
}