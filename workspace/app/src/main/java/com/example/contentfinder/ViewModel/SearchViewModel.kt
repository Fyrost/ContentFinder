package com.example.contentfinder.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.contentfinder.Models.SearchModel
import com.example.contentfinder.Service.RetrofitService

class SearchViewModel: ViewModel() {

    private val mService = RetrofitService()

    fun getResultData(term : String) : MutableLiveData<SearchModel.ResultList>? {
        Log.e("getResultData", "yes")
        return mService.loadResultData(term)
    }
}