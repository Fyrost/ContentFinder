package com.example.contentfinder.Service

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.contentfinder.API.ApiInterface
import com.example.contentfinder.Models.SearchModel
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    val liveUserResponse: MutableLiveData<SearchModel.ResultList> = MutableLiveData()
    companion object Factory {
        var gson = GsonBuilder().setLenient().create()
        val baseUrl = "https://itunes.apple.com/"
        fun create(): ApiInterface {
            Log.e("Retrofit", "create")

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }

    fun loadResultData(term : String, media: String): MutableLiveData<SearchModel.ResultList>? {

        Log.e("loadResultData", "yes")

        val retrofitCall = create().getResults(term, media)

        retrofitCall.enqueue(object : Callback<SearchModel.ResultList> {
            override fun onFailure(call: Call<SearchModel.ResultList>, t: Throwable) {
                Log.e("on Failure :", t.message)
            }

            override fun onResponse(call: Call<SearchModel.ResultList>, response: Response<SearchModel.ResultList>) {

                Log.e("asd : ", response.toString())
                val list = response.body()
                Log.e("Response : ", list.toString())

                liveUserResponse.value = list

                Log.e("hasActiveObservers 1", liveUserResponse.hasActiveObservers().toString()+" check")
                Log.e("on response 2 :", liveUserResponse.toString()+" check")
            }
        })

        return liveUserResponse
    }
}