package com.example.contentfinder.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager

import com.example.contentfinder.Adapter.SectionPagerAdapter
import com.example.contentfinder.R

import kotlinx.android.synthetic.main.main_page.*
import kotlinx.android.synthetic.main.title_bar.*

class MainActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        val sectionsPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = view_pager
        viewPager.offscreenPageLimit = 1
        viewPager.adapter = sectionsPagerAdapter

        tabs.setupWithViewPager(viewPager)

        tabs.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    fun getViewPagerItem(): Int {
        return viewPager.currentItem
    }
}
