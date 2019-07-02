package com.example.contentfinder.controllers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.contentfinder.R
import com.example.contentfinder.adapter.ResultAdapter
import com.example.contentfinder.apiClient.APIService
import com.example.contentfinder.models.SearchModel
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    private val apiServe by lazy {
        APIService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        btn_search.setOnClickListener {
            if(edit_search.text.toString().isNotEmpty()) {
                beginSearch(edit_search.text.toString())
            }
        }
    }

    private fun beginSearch(term: String) {
        val apiInterface = APIService.create().searchResult(term)
        apiInterface.enqueue( object : retrofit2.Callback<SearchModel.Response> {
            override fun onResponse(call: Call<SearchModel.Response>?, response: Response<SearchModel.Response>?) {
                val body = response!!.body()!!.results
                recyclerView.adapter = ResultAdapter(body, this@MainActivity)
            }

            override fun onFailure(call: Call<SearchModel.Response>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, t?.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

}


