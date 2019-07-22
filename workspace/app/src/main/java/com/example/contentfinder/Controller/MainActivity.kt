package com.example.contentfinder.Controller

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager
import android.view.KeyEvent
import android.view.View

import com.example.contentfinder.Adapter.SectionPagerAdapter
import com.example.contentfinder.R
import com.example.contentfinder.ViewModel.SearchViewModel

import kotlinx.android.synthetic.main.main_page.*
import kotlinx.android.synthetic.main.title_bar.*

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var mSearchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        val sectionsPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        viewPager = view_pager
        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        viewPager.offscreenPageLimit = 1
        viewPager.adapter = sectionsPagerAdapter

        tabs.setupWithViewPager(viewPager)

        fab.setOnClickListener {
            showMenu()
//            val myIntent = Intent(this, FavoriteActivity::class.java)
//            startActivity(myIntent)
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
            override fun onPageSelected(position: Int) {
                BodyFragment.getInstance(position)!!.populate()
            }
        })
        editText_search_titleBar.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                BodyFragment.getInstance(viewPager.currentItem)!!.populate()
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
