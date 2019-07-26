package com.example.contentfinder.Controller

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.contentfinder.Adapter.FavoriteAdapter
import com.example.contentfinder.Entity.ResultDatabase
import com.example.contentfinder.Entity.ResultEntity
import com.example.contentfinder.R
import com.example.contentfinder.ViewModel.SearchViewModel

import com.google.android.material.tabs.TabLayout

class FavoriteFragment: Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var favoriteAdapter: FavoriteAdapter
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var mSearchViewModel: SearchViewModel
    lateinit var emptyView: TextView
    private var ctx: Context? = null
    private var favoriteList: ArrayList<ResultEntity> = arrayListOf()

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val SECTION_CATEGORY = "section_category"
        private var instanceList: HashMap<Int, FavoriteFragment> = HashMap()
        @JvmStatic
        fun newInstance(sectionNumber: Int, category: String): FavoriteFragment {
            var instance = FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putString(SECTION_CATEGORY, category)
                }
            }
            instanceList[sectionNumber] = instance
            return instance
        }

        fun getInstance(position: Int): FavoriteFragment? {
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
        recyclerView = view.findViewById(R.id.recycleView_main)
        recyclerView.setHasFixedSize(true)
        gridLayoutManager = GridLayoutManager(ctx, 3)
        recyclerView.layoutManager = gridLayoutManager
        favoriteAdapter = FavoriteAdapter(favoriteList)
        recyclerView.adapter = favoriteAdapter
    }

    fun populate(resultList: List<ResultEntity>) {
        //favoriteList = resultList
        var temp: ArrayList<ResultEntity> = arrayListOf()
        temp.addAll(resultList)
        if (resultList.isEmpty()) {
            var term = activity!!.findViewById<EditText>(R.id.editText_search_titleBar).text.toString()
            var tab = activity!!.findViewById<TabLayout>(R.id.tabs)
            var tabText = tab.getTabAt(arguments!!.getInt(ARG_SECTION_NUMBER))!!.text.toString()
            if (term == null || term == "") {
                emptyView.text = "No ${tabText} in your favorites yet"
            } else {
                emptyView.text = "Sorry, we couldn\'t find \"${tabText}\" for keywords\"${term}\""
            }
            //emptyView.visibility = View.VISIBLE
        } else {
            //emptyView.visibility = View.INVISIBLE
            favoriteAdapter.updateResult(temp)
            favoriteAdapter.notifyDataSetChanged()
        }
    }
}