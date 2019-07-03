package com.example.contentfinder.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contentfinder.Controller.MainActivity
import com.example.contentfinder.Models.SearchModel
import com.example.contentfinder.R
import kotlinx.android.synthetic.main.result_list.view.*

class SearchAdapter(var context: MainActivity,
                    var resultList: SearchModel.ResultList): RecyclerView.Adapter<SearchAdapter.EmpHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.result_list, parent, false)
        return EmpHolder(view)
    }
    override fun getItemCount(): Int {
        return resultList.results.size
    }
    override fun onBindViewHolder(holder:EmpHolder, position: Int) {
        holder.itemView.textView_trackName_row?.text = resultList.results[position].trackName
    }

    class EmpHolder (view: View) : RecyclerView.ViewHolder(view) {
    }
}
