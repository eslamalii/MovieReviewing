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
import com.example.movieapp.databinding.FragmentUpcomingMoviesBinding
import com.example.movieapp.model.movies.popularMovies.PopularMovies
import com.example.movieapp.model.series.genres.Genres
import com.example.movieapp.recyclerViews.GenresRecyclerView
import com.example.movieapp.recyclerViews.TrendingRecyclerViewM
import com.example.movieapp.viewmodel.movies.MoviesViewModel
import kotlin.random.Random


class MoviesFrag : Fragment() {

    private lateinit var viewModel: MoviesViewModel
    private var _binding: FragmentUpcomingMoviesBinding? = null
    lateinit var adapter: GenresRecyclerView
    lateinit var snap: SnapHelper
    lateinit var popView: TrendingRecyclerViewM


    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        viewModel.upComingProgressBar.observe(this, {
            when (it) {
                8 -> {
                    binding.upcomingMoviesLO.visibility = View.GONE
                    binding.progressBarLO.root.visibility = it
                }
                0 -> {
                    binding.upcomingMoviesLO.visibility = it
                    binding.progressBarLO.root.visibility = View.GONE
                }
            }
        })

        viewModel.upcomingMoviesList.observe(this, {
            val random: Int = Random.nextInt(it!!.results.size)
            val objectList = it.results[random]
            binding.titleMo.text = objectList.title

            Glide
                .with(this)
                .load(objectList.getBackPath())
                .fitCenter()
                .into(binding.imageViewMo)
        })

        viewModel.genresList.observe(this, {
            setupRecyclerView(it)
        })

        viewModel.popularList.observe(this, {
            setupRecyclerView1(it, requireContext())
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingMoviesBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView(genres: Genres?) {
        binding.recyclerViewMovies.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = GenresRecyclerView(genres)
        binding.recyclerViewMovies.adapter = adapter
    }

    private fun setupRecyclerView1(list: PopularMovies, context: Context) {
        snap = LinearSnapHelper()
        binding.trendingRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        snap.attachToRecyclerView(binding.trendingRecyclerView)
        popView = TrendingRecyclerViewM(list, context)
        binding.trendingRecyclerView.adapter = popView
    }

}