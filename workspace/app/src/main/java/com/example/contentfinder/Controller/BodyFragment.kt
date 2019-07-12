package com.example.contentfinder.Controller

import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import androidx.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.widget.EditText
import android.widget.TextView

import com.example.contentfinder.Adapter.BodyAdapter
import com.example.contentfinder.Models.SearchModel
import com.example.contentfinder.R
import com.example.contentfinder.Service.RetrofitService
import com.example.contentfinder.ViewModel.SearchViewModel
import kotlinx.android.synthetic.main.body_main.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BodyFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var bodyAdapter: BodyAdapter
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var mSearchViewModel: SearchViewModel
    lateinit var emptyView: TextView
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
        emptyView = view.findViewById(R.id.textView_row_empty)
        emptyView.text = "Browse and find medias at the search bar"
        recyclerView = view.findViewById(R.id.recycleView_main)
        recyclerView.setHasFixedSize(true)
        gridLayoutManager = GridLayoutManager(ctx, 3)
        recyclerView.layoutManager = gridLayoutManager
        bodyAdapter = BodyAdapter(resList)
        recyclerView.adapter = bodyAdapter
    }

    fun populate() {
        var term = activity!!.findViewById<EditText>(R.id.editText_search_titleBar).text.toString()
        var indicator : TextView = activity!!.findViewById(R.id.textView_resultIndicator_main)
        if (term.isNullOrBlank()) {
            indicator.visibility = View.INVISIBLE
            emptyView.visibility = View.VISIBLE
            recyclerView.visibility = View.INVISIBLE
        } else {
            val retrofitCall = RetrofitService.create().getResults(term, arguments!!.getString(SECTION_CATEGORY))
            retrofitCall.enqueue(object : Callback<SearchModel.ResultList> {
                override fun onFailure(call: Call<SearchModel.ResultList>, t: Throwable) {
                    Log.e("on Failure :", t.message)
                }

                override fun onResponse(call: Call<SearchModel.ResultList>, response: Response<SearchModel.ResultList>) {
                    if (resList.isEmpty()) {
                        resList.clear()
                    }
                    println(resList)

                    resList = mSearchViewModel.arrangeResults(response.body())
                    if (resList.size == 0) {
                        emptyView.text = "Sorry, we couldn\'t find any media for \"${term}\""
                        emptyView.visibility = View.VISIBLE
                        recyclerView.visibility = View.INVISIBLE
                        indicator.visibility = View.INVISIBLE
                    } else {
                        emptyView.visibility = View.INVISIBLE
                        recyclerView.visibility = View.VISIBLE
                        indicator.visibility = View.VISIBLE
                        indicator.text = "Results for \"${term}\""
                        bodyAdapter.updateResult(resList)
                        bodyAdapter.notifyDataSetChanged()
                    }
                }
            })
        }
    }
}