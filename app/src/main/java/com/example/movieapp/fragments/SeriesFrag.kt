package com.example.movieapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.FragmentSeriesBinding
import com.example.movieapp.model.series.genres.Genres
import com.example.movieapp.model.series.tvShows.TvShows
import com.example.movieapp.recyclerViews.GenresRecyclerView
import com.example.movieapp.recyclerViews.TrendingRecyclerView
import com.example.movieapp.viewmodel.series.LatestSeriesViewModel


class SeriesFrag : Fragment() {

    private lateinit var viewModel: LatestSeriesViewModel
    private var _binding: FragmentSeriesBinding? = null
    private lateinit var adapter: GenresRecyclerView
    lateinit var ceeeeel: TrendingRecyclerView
    lateinit var snap: SnapHelper

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LatestSeriesViewModel::class.java]
        viewModel.fetchLatestSeries()
        viewModel.fetchGenresSeries()
        viewModel.fetchTvShows()

        viewModel.progressBar.observe(this, {
            when (it) {
                8 -> {
                    binding.upcomingSeriesLO.visibility = View.GONE
                    binding.progressBarLO.root.visibility = it
                }
                0 -> {
                    binding.upcomingSeriesLO.visibility = it
                    binding.progressBarLO.root.visibility = View.GONE
                }
            }
        })

        viewModel.latestSeriesList.observe(this, {
            binding.titleS.text = it!!.name

            Glide
                .with(this)
                .load(it.getBackPath())
                .fitCenter()
                .into(binding.imageViewS)
        })

        viewModel.genresList.observe(this, {

            setupRecyclerView(it)

        })

        viewModel.khod.observe(this, {
            setupRecyclerView1(it, requireContext())
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView(genres: Genres?) {
        binding.recyclerViewSeries.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = GenresRecyclerView(genres)
        binding.recyclerViewSeries.adapter = adapter
    }

    private fun setupRecyclerView1(tvShows: TvShows, context: Context) {
        snap = LinearSnapHelper()
        binding.trendingRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        snap.attachToRecyclerView(binding.trendingRecyclerView)
        ceeeeel = TrendingRecyclerView(tvShows, context)
        binding.trendingRecyclerView.adapter = ceeeeel
    }

}