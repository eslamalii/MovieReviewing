package com.example.movieapp.viewmodel.series

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.data.movies.MoviesServices
import com.example.movieapp.data.series.SeriesService
import com.example.movieapp.model.movies.MovieDetails
import com.example.movieapp.model.series.latestSeries.SeriesModel
import com.example.movieapp.viewmodel.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailsViewModel : BaseViewModel() {

    private val services = SeriesService()

    //Series Details
    private val seriesDetails: MutableLiveData<SeriesModel> = MutableLiveData()

    fun fetchSeriesDetails(id: Int) {
        processBar.postValue(true)
        val series: Observable<SeriesModel> = services.getTvDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        series.subscribe({
            seriesDetails.postValue(it)
        }, {
            Log.d("TAG", "fetchSeriesDetails: $it")
        })
        processBar.postValue(false)
    }

    fun getSeriesDetails(): LiveData<SeriesModel> = seriesDetails
    fun getProgressState(): LiveData<Boolean> = processBar



}