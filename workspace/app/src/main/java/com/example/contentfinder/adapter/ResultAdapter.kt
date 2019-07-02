package com.example.contentfinder.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.contentfinder.R
import com.example.contentfinder.models.SearchModel
import kotlinx.android.synthetic.main.result_row.view.*

class ResultAdapter(val searchModel : List<SearchModel.Result>, val context : Context) : RecyclerView.Adapter<MyViewHolder>() {
    override fun getItemCount(): Int {
        return searchModel.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder  {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.result_row, parent, false)
        return MyViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val resultList = searchModel.get(position)
        holder?.view?.textView_result_title_row?.text = resultList.trackName
        Glide.with(context)
            .load(resultList.artworkUrl100)
            .into(holder?.view?.imageView_result_image_row)
    }
}

class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
}