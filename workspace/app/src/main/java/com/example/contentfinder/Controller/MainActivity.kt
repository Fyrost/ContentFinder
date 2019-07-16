package com.example.contentfinder.Controller

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import com.example.contentfinder.Adapter.BodyAdapter
import com.example.contentfinder.Adapter.SearchAdapter
import com.example.contentfinder.Models.SearchModel
import com.example.contentfinder.R
import com.example.contentfinder.ViewModel.SearchViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.body_main.*

class MainActivity : AppCompatActivity() {
    lateinit var gridLayoutManager : GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        gridLayoutManager = GridLayoutManager(this@MainActivity, 3)
        recycleView_main.layoutManager = gridLayoutManager
        sortResult()

        test_button_main.setOnClickListener {
            val fm = supportFragmentManager
            val fragment = DetailFragment()
            fragment.show(fm,"something")
        }
    }

    fun sortResult() {
        val mSearchViewModel = ViewModelProviders.of(this@MainActivity).get(SearchViewModel::class.java)
        val term = "toy story"
        mSearchViewModel.getResultData(term)?.observe(this, Observer<SearchModel.ResultList> { resultList ->
            val map = HashMap<String, ArrayList<SearchModel.Result>>()
            for (res in resultList!!.results) {
                if (res.kind !== null || res.trackName !== null ) {
                    var kind = map[res.kind]
                    if (kind == null) {
                        kind = ArrayList()
                        map[res.kind] = kind
                    }
                    kind.add(res)
                }
            }
            val newResults = ArrayList<SearchModel.ResultRow>()
            for ((key, value) in map) {
                newResults.add(SearchModel.ResultRow(SearchModel.RowType.HEADER, null, key))
                value.mapTo(newResults) { SearchModel.ResultRow(SearchModel.RowType.VALUES, it, null) }
            }
            recycleView_main.adapter = BodyAdapter(this@MainActivity, newResults)

        })
    }
}
