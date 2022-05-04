package com.example.marvel.presentation.fragment

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.marvel.R
import com.example.marvel.data.CharactersAdapter
import com.example.marvel.presentation.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.fragment_charaterslist.*

class CharacterListFragment: Fragment() {

    private val lifecycleRegistry = LifecycleRegistry(this)

    private lateinit var viewModel : CharacterListViewModel

    private var dialog: Dialog? = null

    private val adapter = CharactersAdapter()

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CharacterListViewModel::class.java)
        subscribeToViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_charaterslist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

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
}