package com.example.movieapp.viewmodel.series

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.series.SeriesService
import com.example.movieapp.model.series.genres.GenresValues
import com.example.movieapp.model.series.latestSeries.SeriesModel
import com.example.movieapp.model.series.tvShows.TvShows
import com.example.movieapp.util.GenreSeries
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers


class LatestSeriesViewModel : ViewModel() {
    private val latestSeriesService = SeriesService()
    val latestSeriesList = MutableLiveData<SeriesModel?>()
    val progressBar = MutableLiveData<Boolean>()
    val tvShows = MutableLiveData<TvShows>()
    val genres = MutableLiveData<List<GenresValues>>()

    init {
        progressBar.value = true
        fetchLatestSeries()
        fetchTvShows()
        fetchGenres()
    }

    private fun fetchLatestSeries() {
        val latestSeries: Observable<SeriesModel> = latestSeriesService.getLatestSeries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        latestSeries.subscribe(
            {
                latestSeriesList.value = it
                progressBar.value = false
            },
            {
                Log.i("TAG", "fetchLatestSeries: $it")
            }
        )


    }

    private fun fetchTvShows() {

        val list: Observable<TvShows> = latestSeriesService.getTvShows()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        list.subscribe({
            tvShows.value = it
        }, {
            Log.i("TAG", "fetchGenresSeries: $it")
        })
    }

    private fun fetchGenres() {
        genres.value = GenreSeries.getAllGenre()
    }
}