package com.example.contentfinder.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log

import com.example.contentfinder.Models.SearchModel
import com.example.contentfinder.Service.RetrofitService

class SearchViewModel: ViewModel() {

    private val mService = RetrofitService()

    fun getResultData(term : String, media : String) : MutableLiveData<SearchModel.ResultList>? {
        Log.e("getResultData", "yes")
        return mService.loadResultData(term, media)
    }

    fun arrangeResults(resultList : SearchModel.ResultList?) : ArrayList<SearchModel.Result> {
        val result: ArrayList<SearchModel.Result> = ArrayList()
        for (res in resultList!!.results) {
            if (res.trackPrice == null) {
                println(res.trackName)
            } else {
                result.add(res)
            }
        }
        return result
    }
}