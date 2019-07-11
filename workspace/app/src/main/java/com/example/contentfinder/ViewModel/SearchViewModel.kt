package com.example.contentfinder.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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