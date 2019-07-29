package com.example.contentfinder.Controller

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import androidx.viewpager.widget.ViewPager

import com.example.contentfinder.Adapter.SectionPagerAdapter
import com.example.contentfinder.R
import com.example.contentfinder.ViewModel.SearchViewModel

import kotlinx.android.synthetic.main.main_page.*
import kotlinx.android.synthetic.main.title_bar.*
import java.util.*
import kotlin.concurrent.schedule

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
            val myIntent = Intent(this, FavoriteActivity::class.java)
            startActivity(myIntent)
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

        editText_search_titleBar.addTextChangedListener(object: TextWatcher{
            var timer = Timer()
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable?) {
                timer.cancel()
                val sleep = 300L
                timer = Timer()
                timer.schedule(sleep) {
                    if (!s.isNullOrEmpty()) {
                        BodyFragment.getInstance(viewPager.currentItem)!!.populate()
                    }
                }
            }
        })
    }
}
