package com.example.contentfinder.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log

import com.example.contentfinder.Models.SearchModel
import com.example.contentfinder.Service.RetrofitService

class SearchViewModel: ViewModel() {

    private val mService = RetrofitService()
    private val mediaList : Array<String> = arrayOf("music", "movie", "tvShow", "audiobook", "musicVideo", "shortFilm")

    fun getResultData(term : String, media : String) : MutableLiveData<SearchModel.ResultList>? {
        Log.e("getResultData", "yes")
        return mService.loadResultData(term, media)
    }

    fun arrangeResults(resultList : SearchModel.ResultList?) : ArrayList<SearchModel.Result> {
        val result: ArrayList<SearchModel.Result> = ArrayList()
        for (res in resultList!!.results) {
            if (res.trackName != "") {
                result.add(res)
            }
        }
        return result
    }

    fun getMediaFilter(filterIndex : Int) : String {
        return mediaList[filterIndex]
    }
}