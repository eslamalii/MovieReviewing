package com.example.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.databinding.ActivityTabMainBinding

class TabMain : AppCompatActivity() {

    private lateinit var binding: ActivityTabMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}