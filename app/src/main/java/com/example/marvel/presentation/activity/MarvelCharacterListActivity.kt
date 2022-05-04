package com.example.marvel.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marvel.R
import com.example.marvel.presentation.fragment.CharacterListFragment

class MarvelCharacterListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvelcharacter_list)

        if(savedInstanceState == null) {
            val characterListFragment = CharacterListFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, characterListFragment).commit()
        }
    }
}