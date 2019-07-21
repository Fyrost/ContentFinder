package com.example.contentfinder.Controller

import android.os.Bundle
import android.os.PersistableBundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.contentfinder.Adapter.FavoritePagerAdapter
import com.example.contentfinder.Adapter.SectionPagerAdapter
import com.example.contentfinder.R
import com.example.contentfinder.ViewModel.SearchViewModel
import kotlinx.android.synthetic.main.main_page.*
import kotlinx.android.synthetic.main.title_bar.*

class FavoriteActivity:  AppCompatActivity() {
    lateinit var viewPager: ViewPager
    lateinit var mSearchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        val favoriteAdapter = FavoritePagerAdapter(this, supportFragmentManager)
        viewPager = view_pager
        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        viewPager.offscreenPageLimit = 1
        viewPager.adapter = favoriteAdapter

        tabs.setupWithViewPager(viewPager)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                FavoriteFragment.getInstance(position)!!.populate()
            }
        })
        editText_search_titleBar.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                FavoriteFragment.getInstance(viewPager.currentItem)!!.populate()
                return@OnKeyListener true
            }
            false
        })
    }
}