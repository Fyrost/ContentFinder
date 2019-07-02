package com.example.contentfinder.apiClient

import com.example.contentfinder.models.SearchModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

const val baseURL = "https://itunes.apple.com/"

interface APIService {

    @GET("search")
    fun searchResult (@Query("term") term: String
//                      @Query("entity") entity: String
    ): Call <SearchModel.Response>

    companion object {
        fun create():APIService{

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseURL)
                .build()

            return  retrofit.create(APIService::class.java)
        }
    }
}