package com.example.marvel.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Characters
import com.example.marvel.R
import com.initishbhatt.marvelsuperheros.api.model.AllCharactersModel
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersAdapter: RecyclerView.Adapter<CharactersViewHolder>() {

    private val _items = mutableListOf<AllCharactersModel>()
    var characters:List<AllCharactersModel> get() = _items.toList()
        set(value) {
        _items.clear()
        if(value != null) _items.addAll(value)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CharactersViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_character , p0, false)
        return CharactersViewHolder(view)
    }

    override fun onBindViewHolder(p0: CharactersViewHolder, p1: Int) {
        p0.bind(_items[p1])
    }

    override fun getItemCount(): Int {
        return _items.size
    }
}

class CharactersViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    fun bind(characters: AllCharactersModel) {
        itemView.character_name_text_view.text = characters.name
        Glide.with(itemView.context)
            .load("${characters.thumbnail.path}.${characters.thumbnail.extension}")
            .into(itemView.character_image_view)
    }
}