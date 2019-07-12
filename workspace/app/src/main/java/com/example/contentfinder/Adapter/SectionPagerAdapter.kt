package com.example.contentfinder.Adapter

import com.example.contentfinder.R

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.example.contentfinder.Controller.BodyFragment

private val TAB_TITLES = arrayOf(
    R.string.music_tab,
    R.string.movie_tab,
    R.string.tv_show_tab,
    R.string.audio_book_tab,
    R.string.music_video_tab,
    R.string.short_film_tab
)

private val mediaList : Array<String> = arrayOf("music", "movie", "tvShow", "audiobook", "musicVideo", "shortFilm")

class SectionPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return BodyFragment.newInstance(position, mediaList[position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}