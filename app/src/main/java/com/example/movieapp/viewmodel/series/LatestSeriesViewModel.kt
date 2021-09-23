package com.example.movieapp.viewmodel.series

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.series.SeriesService
import com.example.movieapp.model.series.genres.Genres
import com.example.movieapp.model.series.latestSeries.LatestSeries
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers


class LatestSeriesViewModel : ViewModel() {
    private val latestSeriesService = SeriesService()
    val latestSeriesList = MutableLiveData<LatestSeries?>()
    val progressBar = MutableLiveData<Int>()
    val genresList = MutableLiveData<Genres?>()

    fun fetchLatestSeries() {
        progressBar.value = 8
        val latestSeries: Observable<LatestSeries> = latestSeriesService.getLatestSeries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        latestSeries.subscribe(
            {
                latestSeriesList.value = it
                progressBar.value = 0
            },
            {
                Log.i("TAG", "fetchLatestSeries: $it")
            }
        )


    }

    fun fetchGenresSeries() {
        val genresObservable: Observable<Genres> = latestSeriesService.getGenresSeries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        genresObservable.subscribe(
            {
                genresList.value = it
            }, {
                Log.i("TAG", "fetchGenresSeries: $it")
            }
        )
    }
}