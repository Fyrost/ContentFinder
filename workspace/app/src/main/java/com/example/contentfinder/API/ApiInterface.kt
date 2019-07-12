package com.example.contentfinder.API

import com.example.contentfinder.Models.SearchModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("search")
    fun getResults(@Query("term") term : String, @Query("media") media : String): Call<SearchModel.ResultList>
}