package com.example.contentfinder.Controller

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.contentfinder.Adapter.SearchAdapter
import com.example.contentfinder.Models.SearchModel
import com.example.contentfinder.R
import com.example.contentfinder.ViewModel.SearchViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView_main.layoutManager = linearLayoutManager
        recyclerView_main.hasFixedSize()

        button_search.setOnClickListener {
            val term = editText_search.text.toString()
            getResults(term)
        }
        test_button_main.setOnClickListener {
            showMenu()
        }
    }

    private fun getResults(term : String) {
        Log.e("getResults", "yes")
        val mSearchViewModel = ViewModelProviders.of(this@MainActivity).get(SearchViewModel::class.java)

        mSearchViewModel.getResultData(term)?.observe(this, Observer<SearchModel.ResultList> { resultList ->
            recyclerView_main.adapter = SearchAdapter(this@MainActivity, resultList as SearchModel.ResultList)
        })
    }

    private fun showMenu(){
        val fm = supportFragmentManager
        val fragment = MenuFragment()
        fragment.show(fm,"something")
    }
}
