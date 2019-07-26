package com.example.contentfinder.Adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.example.contentfinder.Controller.DescriptionActivity
import com.example.contentfinder.Models.SearchModel
import com.example.contentfinder.R
import com.example.contentfinder.ViewModel.SearchViewModel

import kotlinx.android.synthetic.main.body_row_main.view.*

class BodyAdapter(resultList1 : ArrayList<SearchModel.Result>) : RecyclerView.Adapter<BodyAdapter.TempHolder>() {

    private var resultList: ArrayList<SearchModel.Result> = resultList1

    fun updateResult(res: ArrayList<SearchModel.Result>) {
        resultList.clear()
        resultList = res
        notifyDataSetChanged()
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
        holder.setItemDetails(resultList[position])
    }

    class TempHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setItemDetails(result: SearchModel.Result) {
            var trackName = result.trackName
            var trackImg = result.artworkUrl100
            var trackPrice = "$ " + result.trackPrice
            var trackGenre = result.primaryGenreName
            Glide.with(itemView.context)
                .load(trackImg)
                .into(itemView.imageView_trackImg_row)
            itemView.textView_trackName_row.text = trackName
            itemView.textView_trackGenre_row.text = trackGenre
            itemView.textView_trackPrice_row.text = trackPrice
            itemView.setOnClickListener{ view ->
                val intent = Intent(itemView.context, DescriptionActivity::class.java)
                putResults(intent, result)
                itemView.context.startActivity(intent)
            }
        }

        fun putResults(intent: Intent, result: SearchModel.Result): Intent {
            intent.putExtra("trackId", result.trackId)
            intent.putExtra("trackName", result.trackName)
            intent.putExtra("trackImg", result.artworkUrl100)
            intent.putExtra("trackPrice", result.trackPrice)
            intent.putExtra("trackGenre", result.primaryGenreName)
            intent.putExtra("trackArtist", result.artistName)
            intent.putExtra("trackCategory", result.kind)
            intent.putExtra("trackDescription", result.longDescription)
            intent.putExtra("trackYear", result.releaseDate)
            return intent
        }
    }
}