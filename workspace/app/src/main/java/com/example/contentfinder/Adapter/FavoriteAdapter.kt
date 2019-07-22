package com.example.contentfinder.Adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.example.contentfinder.R

import kotlinx.android.synthetic.main.body_row_main.view.*

class FavoriteAdapter(var favoriteList1 : ArrayList<HashMap<String, String>>) : RecyclerView.Adapter<FavoriteAdapter.TempHolder>() {

    private var favoriteList: ArrayList<HashMap<String, String>> = favoriteList1

    fun updateResult(res: ArrayList<HashMap<String, String>>) {
        favoriteList.clear()
        favoriteList = res
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.body_row_main, parent,false)
        return TempHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    override fun onBindViewHolder(holder: TempHolder, position: Int) {
        val holderItemView = holder.itemView
        val result = favoriteList[position]

        val trackName = result["trackName"]
        val trackImg = result["artworkUrl100"]
        val trackPrice = "$ " + result["trackPrice"]
        val trackGenre = result["primaryGenreName"]

        Glide.with(holderItemView.context)
            .load(trackImg)
            .into(holderItemView.imageView_trackImg_row)
        holderItemView.textView_trackName_row.text = trackName
        holderItemView.textView_trackGenre_row.text = trackGenre
        holderItemView.textView_trackPrice_row.text = trackPrice
    }

    class TempHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}