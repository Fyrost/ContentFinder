package com.example.contentfinder.Controller

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.EditText

import com.example.contentfinder.Adapter.BodyAdapter
import com.example.contentfinder.Models.SearchModel
import com.example.contentfinder.R
import com.example.contentfinder.Service.RetrofitService
import com.example.contentfinder.ViewModel.SearchViewModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BodyFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var bodyAdapter: BodyAdapter
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var mSearchViewModel: SearchViewModel
    private var resList: ArrayList<SearchModel.Result> = arrayListOf()
    private var ctx: Context? = null

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val SECTION_CATEGORY = "section_category"
        private var instanceList: HashMap<Int, BodyFragment> = HashMap()
        @JvmStatic
        fun newInstance(sectionNumber: Int, category: String): BodyFragment {
            var instance = BodyFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putString(SECTION_CATEGORY, category)
                }
            }
            instanceList[sectionNumber] = instance
            return instance
        }

        fun getInstance(position: Int): BodyFragment? {
            return instanceList[position]
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        ctx = context
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        return inflater.inflate(R.layout.body_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycleView_main)
        recyclerView.setHasFixedSize(true)
        gridLayoutManager = GridLayoutManager(ctx, 3)
        recyclerView.layoutManager = gridLayoutManager
        bodyAdapter = BodyAdapter(resList)
        recyclerView.adapter = bodyAdapter
    }

    fun populate() {
        var term = activity!!.findViewById<EditText>(R.id.editText_search_titleBar).text.toString()
        val retrofitCall = RetrofitService.create().getResults(term, arguments!!.getString(SECTION_CATEGORY))
        retrofitCall.enqueue(object : Callback<SearchModel.ResultList> {
            override fun onFailure(call: Call<SearchModel.ResultList>, t: Throwable) {
                Log.e("on Failure :", t.message)
            }

            override fun onResponse(call: Call<SearchModel.ResultList>, response: Response<SearchModel.ResultList>) {
                if (resList.isEmpty()) {
                    resList.clear()
                }

                resList = mSearchViewModel.arrangeResults(response.body())
                bodyAdapter.updateResult(resList)
                bodyAdapter.notifyDataSetChanged()
            }
        })
    }
}