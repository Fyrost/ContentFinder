package com.example.contentfinder.Adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.example.contentfinder.Models.SearchModel
import com.example.contentfinder.R

import kotlinx.android.synthetic.main.body_row_main.view.*

class BodyAdapter(var resultList1 : ArrayList<SearchModel.Result>) : RecyclerView.Adapter<BodyAdapter.TempHolder>() {

    private var resultList: ArrayList<SearchModel.Result> = resultList1

    fun updateResult(res: ArrayList<SearchModel.Result>) {
        resultList.clear()
        resultList = res
        notifyDataSetChanged()
    }

    fun getResults(): ArrayList<SearchModel.Result> {
        return resultList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.body_row_main, parent,false)
        return TempHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: TempHolder, position: Int) {
        val holderItemView = holder.itemView
        val result = resultList[position]
        val trackName = result.trackName
        val collectionName = result.collectionName
        val trackImg = result.artworkUrl100
        val trackPrice = "$ " + result.trackPrice
        val trackGenre = result.primaryGenreName

        Glide.with(holderItemView.context)
            .load(trackImg)
            .into(holderItemView.imageView_trackImg_row)

        when {
            trackName != null -> holderItemView.textView_trackName_row.text = trackName
            collectionName != null -> holderItemView.textView_trackName_row.text = collectionName
            else -> holderItemView.textView_trackName_row.visibility = View.GONE
        }

        if(trackGenre != null)
            holderItemView.textView_trackGenre_row.text = trackGenre
        else
            holderItemView.textView_trackGenre_row.visibility = View.GONE

        holderItemView.textView_trackPrice_row.text = if(trackPrice == "$ null")  "FREE" else trackPrice
    }

    class TempHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}