package com.example.movieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.FragmentScreenDetailsBinding
import com.example.movieapp.model.series.latestSeries.SeriesModel
import com.example.movieapp.view.OverlapLoadingView
import com.example.movieapp.viewmodel.series.DetailsViewModel
import com.google.android.material.snackbar.Snackbar

class ScreenDetailsFrag : Fragment(R.layout.fragment_screen_details) {
    private var _binding: FragmentScreenDetailsBinding? = null
    private lateinit var viewModel: DetailsViewModel
    private var seriesID: Int? = null
    private var snackBar: Snackbar? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        seriesID = requireArguments().getInt("id")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchSeriesDetails(seriesID!!)
        obsState()
        viewModel.getSeriesDetails().observe(viewLifecycleOwner, {
            updateUI(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScreenDetailsBinding.inflate(inflater, container, false)
        return _binding!!.root
    }


    private fun updateUI(data: SeriesModel) {
        if (data != null) {

            binding.nameHol.text = data.name
            binding.storyHol.text = data.overview

            if (data.genres.isNotEmpty()) {
                val genres = data.genres[0].name
                binding.genreHol.text = genres
            }

            binding.rateHol.text = data.voteAverage.toString()

            Glide
                .with(this)
                .load(data.getPosterUri())
                .into(binding.posterHol)

            Glide
                .with(this)
                .load(data.getBackPath())
                .into(binding.backdropPathHol)

            binding.loadingView.loadingStateType(OverlapLoadingView.STATETYPE.DONE)
            binding.constraintLayout.visibility = View.VISIBLE
        } else {
            binding.loadingView.loadingStateType(OverlapLoadingView.STATETYPE.ERROR)
            snackBar =
                Snackbar.make(
                    binding.constraintLayout,
                    "Connection Error",
                    Snackbar.LENGTH_INDEFINITE
                )
        }
    }

    private fun obsState() {
        viewModel.getProgressState().observe(viewLifecycleOwner, {
            if (it) {
                binding.loadingView.loadingStateType(OverlapLoadingView.STATETYPE.LOADING)
            }
        })
    }
}