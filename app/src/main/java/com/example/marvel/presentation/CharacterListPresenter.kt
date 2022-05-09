package com.example.marvel.presentation

import android.util.Log
import com.example.data.datasource.CharacterRepository
import com.example.domain.model.Characters
import com.example.marvel.di.CharactersListView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import net.grandcentrix.thirtyinch.TiPresenter
import net.grandcentrix.thirtyinch.rx2.RxTiPresenterDisposableHandler
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CharacterListPresenter : TiPresenter<CharactersListView>() {

    companion object {
        @JvmStatic val TAG = CharacterListPresenter::class.java.simpleName!!
    }

//    @Inject lateinit var presenterConfig: PresenterConfig
    @Inject lateinit var repository: CharacterRepository

    private val rxHandler = RxTiPresenterDisposableHandler(this)

    private val characterCache: MutableList<Characters> = mutableListOf()

    /**
     * Called when the view was attached to this presenter (when it is available)
     */
    override fun onAttachView(view: CharactersListView) {
        super.onAttachView(view)

        // Listen to view based events
        subscribeToView(view)

        if (characterCache.isEmpty()) {
            // load the character data
            loadCharacters(view)
        } else {
            renderCharacters(view, characterCache)
        }
    }

    /**
     * Subscribes to every view Observable.
     */
    private fun subscribeToView(view: CharactersListView) {
        // Reacts to the reload click and gets some new Characters - yay!
        rxHandler.manageViewDisposable(view.onReloadClick()
                // clickDebounce will provide a buffer if the user plays monkey on the reload button
//                .debounce(presenterConfig.clickDebounce, TimeUnit.MILLISECONDS)
                // Cheap way to trigger a reload of the Charactergies
                .subscribe({ loadCharacters(view) })
        )
    }

    /**
     * Creates the Character loading logic wrapped in a Single.
     * Will also tell the view to show the loading indicator
     */
    private fun createCharacterLoader(): Single<List<Characters>> {
        return Single.fromCallable { view!!.getViewModel().setLoading(true) }
                .subscribeOn(AndroidSchedulers.mainThread())
                .flatMap<List<Characters>> { _ -> repository.getCharacters() }
                .map { characters ->
                    characterCache.clear()
                    characterCache.addAll(characters)
                    return@map characters
                }
    }

    /**
     * Loads the Characters from the repository and sets the result in the viewmodel.
     * Also disables the loading indicator in the view.
     */
    private fun loadCharacters(view: CharactersListView) {
        rxHandler.manageDisposable(createCharacterLoader()
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .onErrorReturn { throwable ->
                    Log.e(TAG, "Could not load cute little pictures.", throwable)
                    return@onErrorReturn listOf()
                }
                .subscribe { characters ->
                    renderCharacters(view, characters)
                }
        )
    }

    private fun renderCharacters(view: CharactersListView, character: List<Characters>) {
        val viewModel = view.getViewModel()
        viewModel.setCharacters(character)
        viewModel.setLoading(false)
    }
}
