package com.example.marvel.presentation.fragment

import android.app.Dialog
import android.app.ProgressDialog
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LifecycleRegistryOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.*
import com.example.data.datasource.CharacterModule
import com.example.data.retrofit.NetworkModule
import com.example.marvel.BaseApplication
import com.example.marvel.R
import com.example.marvel.di.CharactersListComponent
import com.example.marvel.di.CharactersListView
import com.example.marvel.di.DaggerCharactersListComponent
import com.example.marvel.presentation.CharacterListPresenter
import com.example.marvel.presentation.adapter.CharactersAdapter
import com.example.marvel.presentation.viewmodel.CharacterListViewModel
import com.pascalwelsch.compositeandroid.fragment.CompositeFragment
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_charaterslist.*
import net.grandcentrix.thirtyinch.internal.TiPresenterProvider
import net.grandcentrix.thirtyinch.plugin.TiFragmentPlugin

class CharacterListFragment: CompositeFragment(), LifecycleRegistryOwner, CharactersListView {

    private val lifecycleRegistry = LifecycleRegistry(this)

    private lateinit var viewModel : CharacterListViewModel

    private var dialog: Dialog? = null

    private val adapter = CharactersAdapter()

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

    private val presenter = CharacterListPresenter()
    init {
        // Adds the MVP framework to our fragment - we could also extend TiFragment, but I am no fan of inheritance
        addPlugin(TiFragmentPlugin<CharacterListPresenter, CharactersListView>(TiPresenterProvider { presenter }))
    }

    private val component: CharactersListComponent by lazy {
        DaggerCharactersListComponent.builder()
            .appModule(BaseApplication.module())
            .characterModule(CharacterModule())
            .networkModule(NetworkModule())
            .build()
    }

    private lateinit var onReloadClickSubject: PublishSubject<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setHasOptionsMenu(true)

        onReloadClickSubject = PublishSubject.create()

        component.inject(this)

        component.inject(presenter)

        viewModel = ViewModelProviders.of(this).get(CharacterListViewModel::class.java)

        subscribeToViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_charaterslist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // View injected by kotlin-android-extentions
        character_recycler_view.adapter = adapter

        val layoutManager: RecyclerView.LayoutManager
        // set the layout manager and some props on the RecyclerView
        if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // create a layout manager
            layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
        } else {
            layoutManager = GridLayoutManager(context, 2)
            character_recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
        }

        character_recycler_view.layoutManager = layoutManager
        character_recycler_view.setHasFixedSize(true)

        // Improve scrolling with a SnapHelper
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(character_recycler_view)
    }

    override fun onDestroy() {
        onReloadClickSubject.onComplete()
        super.onDestroy()
    }

//    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater!!.inflate(R.menu.character_list, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when (item!!.itemId) {
//            R.id.action_reload -> {
//                // Sends the click event through the onReloadClickSubject to the subscribing presenter
//                onReloadClickSubject.onNext(Object())
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

    private fun subscribeToViewModel() {
        viewModel.getCharacters().observe(this, Observer { characters ->
            adapter.characters = characters!!
        })

        viewModel.isLoading().observe(this, Observer { loading ->

            if(loading ==null) return@Observer

            character_recycler_view.visibility = if (loading) View.GONE else View.VISIBLE

            if(dialog != null) {
                dialog?.dismiss()
                dialog = null
            }

            if(loading) {
                dialog = ProgressDialog.show(context, "Loading", "Please wait ...", true)
            }
        })
    }

    override fun getViewModel(): CharacterListViewModel {
        return viewModel
    }

    override fun onReloadClick(): Observable<Any> {
        return onReloadClickSubject
    }
}