package com.example.movieapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentUpcomingMoviesBinding
import com.example.movieapp.model.movies.popularMovies.PopularMovies
import com.example.movieapp.model.series.genres.GenresValues
import com.example.movieapp.recyclerViews.GenresRecyclerView
import com.example.movieapp.recyclerViews.TrendingRecyclerViewM
import com.example.movieapp.recyclerViews.TrendingRecyclerViewM.OnItemClickListener
import com.example.movieapp.viewmodel.movies.MoviesViewModel
import kotlin.random.Random


class MoviesFrag : Fragment(), OnItemClickListener {

    private lateinit var viewModel: MoviesViewModel
    private var _binding: FragmentUpcomingMoviesBinding? = null
    private lateinit var adapter: GenresRecyclerView
    private lateinit var snap: SnapHelper
    private lateinit var popView: TrendingRecyclerViewM


    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        viewModel.upComingProgressBar.observe(this, {
            when (it) {
                true -> {
                    binding.upcomingMoviesLO.visibility = View.GONE
                    binding.progressBarLO.root.visibility = View.VISIBLE
                }
                false -> {
                    binding.upcomingMoviesLO.visibility = View.VISIBLE
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

        viewModel.popularList.observe(this, {
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
        _binding = FragmentUpcomingMoviesBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView(genres: List<GenresValues>) {
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
        popView = TrendingRecyclerViewM(list, context, this)
        binding.trendingRecyclerView.adapter = popView
    }

    override fun onItemClicked(position: Int) {
//        findNavController().navigate(R.id.screenDetailsFrag)
    }

}