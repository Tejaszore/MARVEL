package com.example.marvel.di

import com.example.marvel.presentation.viewmodel.CharacterListViewModel
import net.grandcentrix.thirtyinch.TiView
import io.reactivex.Observable

/**
 * This view connects the view implementation with a presenter.
 */
interface CharactersListView : TiView {

    fun getViewModel(): CharacterListViewModel
    fun onReloadClick(): Observable<Any>
}