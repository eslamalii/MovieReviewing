package com.example.movieapp.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieapp.fragments.MoviesFrag
import com.example.movieapp.fragments.MyListFrag
import com.example.movieapp.R
import com.example.movieapp.fragments.SeriesFrag

public val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

class SectionsPagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int = TAB_TITLES.size

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> SeriesFrag()
            1 -> MoviesFrag()
            2 -> MyListFrag()
            else -> Fragment()
        }
    }

}