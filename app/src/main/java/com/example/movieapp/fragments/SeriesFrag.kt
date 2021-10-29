package com.example.movieapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSeriesBinding
import com.example.movieapp.model.series.genres.GenresValues
import com.example.movieapp.model.series.tvShows.Results
import com.example.movieapp.model.series.tvShows.TvShows
import com.example.movieapp.recyclerViews.GenresRecyclerView
import com.example.movieapp.recyclerViews.TrendingRecyclerView
import com.example.movieapp.viewmodel.series.LatestSeriesViewModel


class SeriesFrag : Fragment(), TrendingRecyclerView.OnItemCLickListener {

    private lateinit var viewModel: LatestSeriesViewModel
    private var _binding: FragmentSeriesBinding? = null
    private lateinit var adapter: GenresRecyclerView
    private lateinit var trendingView: TrendingRecyclerView
    private lateinit var snap: SnapHelper
    lateinit var navController: NavController

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LatestSeriesViewModel::class.java]

        viewModel.progressBar.observe(this, {
            when (it) {
                true -> {
                    binding.upcomingSeriesLO.visibility = View.GONE
                    binding.progressBarLO.root.visibility = View.VISIBLE
                }
                false -> {
                    binding.upcomingSeriesLO.visibility = View.VISIBLE
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

        viewModel.tvShows.observe(this, {
            setupRecyclerView1(it, requireContext())
        })

        viewModel.genres.observe(this, {
            setupRecyclerView(it)
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

    private fun setupRecyclerView(genres: List<GenresValues>) {
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
        trendingView = TrendingRecyclerView(tvShows, context, this)
        binding.trendingRecyclerView.adapter = trendingView
    }

    override fun onItemClicked(position: Results) {
        findNavController().navigate(
            R.id.action_itemTabsFragment_to_screenDetailsFrag,
            bundleOf("id" to position.id)
        )
    }
}