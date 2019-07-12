package com.example.contentfinder.Controller

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.KeyEvent
import android.widget.EditText

import com.example.contentfinder.Adapter.BodyAdapter
import com.example.contentfinder.Controller0.BodyViewModel
import com.example.contentfinder.Models.SearchModel
import com.example.contentfinder.R
import com.example.contentfinder.ViewModel.SearchViewModel

class BodyFragment : Fragment() {

    private lateinit var pageViewModel: BodyViewModel
    private lateinit var mSearchViewModel: SearchViewModel
    private var isListenerSet : Boolean = false
    private var secNum : Int? = 0

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(sectionNumber: Int): BodyFragment {
            return BodyFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    secNum = sectionNumber
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycleView_main)
        val searchBar: EditText = activity!!.findViewById(R.id.editText_search_titleBar)
        if (!isListenerSet) {
            searchBar.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    var term = activity!!.findViewById<EditText>(R.id.editText_search_titleBar).text.toString()
                    var temp = mSearchViewModel.getResultData(term, mSearchViewModel.getMediaFilter(secNum!!))
                    temp?.observe(activity!!, Observer<SearchModel.ResultList> { resultList ->
                        recyclerView.adapter = BodyAdapter(mSearchViewModel.arrangeResults(resultList))
                    })
                    return@OnKeyListener true
                }
                false
            })
            activity!!.findViewById<TabLayout>(R.id.tabs).addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    if (tab.position == arguments?.getInt(ARG_SECTION_NUMBER)) {
                        var term = activity!!.findViewById<EditText>(R.id.editText_search_titleBar).text.toString()
                        var temp = mSearchViewModel.getResultData(term, mSearchViewModel.getMediaFilter(tab.position))
                        temp?.observe(activity!!, Observer<SearchModel.ResultList> { resultList ->
                            recyclerView.adapter = BodyAdapter(mSearchViewModel.arrangeResults(resultList))
                            println(arguments?.getInt(ARG_SECTION_NUMBER).toString())
                        })
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })
            isListenerSet = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(BodyViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
            secNum = arguments?.getInt(ARG_SECTION_NUMBER)
        }
        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.body_main, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recycleView_main)
        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        return root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }

//    fun displayData(recyclerView: RecyclerView, media : String) {
//        recyclerView.layoutManager = GridLayoutManager(activity, 3)
//        recyclerView.adapter = bodyAdapter
//        var term = activity!!.findViewById<EditText>(R.id.editText_search_titleBar).text.toString()
//        mSearchViewModel.getResultData(term, media)?.observe(activity!!, Observer<SearchModel.ResultList> { resultList ->
//            results.addAll(mSearchViewModel.arrangeResults(resultList))
//        })
//        bodyAdapter.notifyDataSetChanged()
//    }
//
}