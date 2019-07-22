package com.example.contentfinder.Controller

<<<<<<< HEAD
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import com.example.contentfinder.Adapter.BodyAdapter
import com.example.contentfinder.Adapter.SearchAdapter
import com.example.contentfinder.Models.SearchModel
=======
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
>>>>>>> develop
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

<<<<<<< HEAD
        gridLayoutManager = GridLayoutManager(this@MainActivity, 3)
        recycleView_main.layoutManager = gridLayoutManager
        sortResult()

        test_button_main.setOnClickListener {
            val fm = supportFragmentManager
            val fragment = DetailFragment()
            fragment.show(fm,"something")
        }
    }
=======
        val sectionsPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        viewPager = view_pager
        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        viewPager.offscreenPageLimit = 1
        viewPager.adapter = sectionsPagerAdapter
>>>>>>> develop

        tabs.setupWithViewPager(viewPager)

        fab.setOnClickListener {
            val myIntent = Intent(this, FavoriteActivity::class.java)
            startActivity(myIntent)
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

<<<<<<< HEAD
        })
    }
=======
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

>>>>>>> develop
}
