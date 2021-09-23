package com.example.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.databinding.ActivityTabMainBinding
import com.example.movieapp.ui.main.SectionsPagerAdapter
import com.example.movieapp.ui.main.TAB_TITLES
import com.google.android.material.tabs.TabLayoutMediator

class TabMain : AppCompatActivity() {

    private lateinit var binding: ActivityTabMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTabMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.viewPager.isUserInputEnabled = false
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = String.format(getString(TAB_TITLES[position]))
        }.attach()
//        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
//        val viewPager: ViewPager = binding.viewPager
//        viewPager.adapter = sectionsPagerAdapter
//        tabs.setupWithViewPager(viewPager)


    }
}