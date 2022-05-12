package com.example.marvel.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marvel.R
import com.initishbhatt.marvelsuperheros.api.model.AllCharactersModel
import kotlinx.android.synthetic.main.item_character.view.*

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var listData: List<AllCharactersModel>? = null

    fun setUpdatedData(listData: List<AllCharactersModel>) {
        this.listData = listData
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView = view.character_image_view
        val textviewName = view.character_name_text_view
//        val textviewDescription = view.textviewDescription

        fun bind(data: AllCharactersModel) {
            textviewName.setText(data.name)
//            textviewDescription.setText(data.description)

            Glide.with(imageView)
                .load("${data.thumbnail.path}.${data.thumbnail.extension}")
                .apply(RequestOptions.centerCropTransform())
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character , parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(listData == null)return 0
        else return listData?.size!!
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }
}