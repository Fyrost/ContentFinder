package com.example.contentfinder.Controller

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.viewpager.widget.ViewPager
import com.example.contentfinder.Adapter.FavoritePagerAdapter
import com.example.contentfinder.Entity.ResultDatabase
import com.example.contentfinder.Entity.ResultEntity
import com.example.contentfinder.R
import com.example.contentfinder.ViewModel.SearchViewModel
import kotlinx.android.synthetic.main.main_page.*
import kotlinx.android.synthetic.main.title_bar.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteActivity:  AppCompatActivity() {
    lateinit var viewPager: ViewPager
    lateinit var mSearchViewModel: SearchViewModel
    lateinit var db: ResultDatabase

    private val mediaList : Array<String> = arrayOf("song", "feature-movie", "tv-episode", "audiobook", "music-video", "Short Film")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        val favoriteAdapter = FavoritePagerAdapter(this, supportFragmentManager)
        viewPager = view_pager
        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        viewPager.offscreenPageLimit = 1
        viewPager.adapter = favoriteAdapter
        db = Room.databaseBuilder(
            applicationContext,
            ResultDatabase::class.java, "results.db"
        ).build()

        tabs.setupWithViewPager(viewPager)

        fab.setOnClickListener {
            showMenu()
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                var term = editText_search_titleBar.text.toString()
                var resultList: List<ResultEntity> = listOf()
                var test = GlobalScope.launch {
                    if (term.isNullOrBlank()) {
                        resultList = db.resultDao().findByKind(mediaList[position])
                    } else {
                        resultList = db.resultDao().findByKindAndTerm(term, mediaList[position])
                    }

                    println(db.resultDao().getAll())
                }
                while(test.isActive) {

                }
                FavoriteFragment.getInstance(viewPager.currentItem)!!.populate(resultList)
            }
        })
        editText_search_titleBar.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                var term = editText_search_titleBar.text.toString()
                GlobalScope.launch {
                    var resultList = db.resultDao().findByKindAndTerm(term, mediaList[viewPager.currentItem])
                    FavoriteFragment.getInstance(viewPager.currentItem)!!.populate(resultList)
                }
                return@OnKeyListener true
            }
            false
        })
    }

    private fun showMenu(){
        val fm = supportFragmentManager
        val fragment = MenuFragment()
        fragment.show(fm,"something")
    }

}