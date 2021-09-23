package com.example.movieapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.FragmentUpcomingMoviesBinding
import com.example.movieapp.model.series.genres.Genres
import com.example.movieapp.recyclerViews.GenresRecyclerView
import com.example.movieapp.viewmodel.movies.UpcomingViewModel
import kotlin.random.Random


class MoviesFrag : Fragment() {

    private lateinit var viewModel: UpcomingViewModel
    private var _binding: FragmentUpcomingMoviesBinding? = null
    lateinit var adapter: GenresRecyclerView

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[UpcomingViewModel::class.java]
        viewModel.fetchUpcomingMovies()
        viewModel.fetchGenresSeries()

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
            setupRecyclerView(requireContext(), it)
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

    private fun setupRecyclerView(context: Context, genres: Genres?) {
        binding.recyclerViewMovies.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = GenresRecyclerView(genres, context)
        binding.recyclerViewMovies.adapter = adapter
    }

}