package com.example.contentfinder.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.contentfinder.Models.SearchModel
import com.example.contentfinder.R
import kotlinx.android.synthetic.main.body_header_main.view.*
import kotlinx.android.synthetic.main.body_row_main.view.*

class BodyAdapter (val context : Context, var resultList : ArrayList<SearchModel.ResultRow>) : RecyclerView.Adapter<BodyAdapter.TempHolder>() {
    private var filteredResults = ArrayList<SearchModel.ResultRow>()
    private var filtering = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val inflatedView : View = when (viewType) {
            SearchModel.RowType.VALUES.ordinal -> layoutInflater.inflate(R.layout.body_row_main, parent,false)
            else -> layoutInflater.inflate(R.layout.body_header_main, parent,false)
        }
        return TempHolder(inflatedView)
    }

    override fun getItemViewType(position: Int) =
        if (filtering) {
            filteredResults[position].type.ordinal
        } else {
            resultList[position].type.ordinal
        }

    override fun getItemCount(): Int {
        if (filtering) {
            return filteredResults.size
        }
        return resultList.size
    }

    override fun onBindViewHolder(holder: TempHolder, position: Int) {
        println(resultList[position])
        println(position)
        val resultRow : SearchModel.ResultRow = if (filtering) {
            filteredResults[position]
        } else {
            resultList[position]
        }
        val holderItemView = holder.itemView
        if (resultRow.type == SearchModel.RowType.VALUES) {
            val result = resultRow.res
            val trackName = result?.trackName
            val trackImg = result?.artworkUrl60
            val trackPrice = "$ "+result?.trackPrice
            val trackGenre = result?.primaryGenreName
            if (trackName !== null) {
                Glide.with(holderItemView.context)
                    .load(trackImg)
                    .into(holderItemView.imageView_trackImg_row)
                holderItemView.textView_trackName_row.text = trackName
                holderItemView.textView_trackGenre_row.text = trackGenre
                holderItemView.textView_trackPrice_row.text = trackPrice
            }
        } else {
            holderItemView.textView_trackEntity_header.text = resultRow.header
        }
    }

    class TempHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

}