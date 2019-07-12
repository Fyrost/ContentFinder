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
import com.example.contentfinder.Adapter.FavoriteAdapter
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
    private var popu: ArrayList<HashMap<String, String>> = arrayListOf()

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
        favoriteAdapter = FavoriteAdapter(popu)
        recyclerView.adapter = favoriteAdapter
    }

    fun populate() {
        var term = activity!!.findViewById<EditText>(R.id.editText_search_titleBar).text.toString()
        generateData()
        var temp1 = searchTerm(term)
        var tab = activity!!.findViewById<TabLayout>(R.id.tabs)
        if (temp1.size == 0) {
            var tabText = tab.getTabAt(arguments!!.getInt(ARG_SECTION_NUMBER))!!.text
            emptyView.text = "No ${tabText} in your favorites yet"
            emptyView.visibility = View.VISIBLE
            recyclerView.visibility = View.INVISIBLE
        } else {
            emptyView.visibility = View.INVISIBLE
            recyclerView.visibility = View.VISIBLE
            favoriteAdapter.updateResult(temp1)
            favoriteAdapter.notifyDataSetChanged()
        }
    }

    fun generateData() {
        var tempArray: ArrayList<HashMap<String, String>> = arrayListOf()
        var temp: HashMap<String, String> = hashMapOf()
        for (i in 0 until 10) {
            temp["trackName"] = "John Wick "+i
            temp["artworkUrl100"] = "https://is1-ssl.mzstatic.com/image/thumb/Music128/v4/fd/51/e8/fd51e8a6-cd10-271b-2ecb-a6ed69fc0fca/source/100x100bb.jpg"
            temp["trackPrice"] = "$ 10.00"
            temp["primaryGenreName"] = "Action"
            tempArray.add(temp)
        }
        popu = tempArray
    }

    fun searchTerm(term: String) : ArrayList<HashMap<String, String>> {
        var tempArray : ArrayList<HashMap<String, String>> = arrayListOf()
        for (row in popu) {
            if (row["trackName"]!!.startsWith(term, 0, true)) {
                tempArray.add(row)
            }
        }
        return tempArray
    }
}