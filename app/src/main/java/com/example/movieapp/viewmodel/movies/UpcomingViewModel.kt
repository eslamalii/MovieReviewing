package com.example.movieapp.viewmodel.movies

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.movies.UpcomingService
import com.example.movieapp.model.movies.upcomingMovies.UpcomingMovies
import com.example.movieapp.model.series.genres.Genres
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class UpcomingViewModel : ViewModel() {
    private val upcomingMoviesService = UpcomingService()
    val upcomingMoviesList = MutableLiveData<UpcomingMovies?>()
    val upComingProgressBar = MutableLiveData<Int>()
    val genresList = MutableLiveData<Genres?>()

    fun fetchUpcomingMovies() {
        upComingProgressBar.value = View.GONE
        val upcomingMovies: Observable<UpcomingMovies> = upcomingMoviesService.getUpcomingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        upcomingMovies.subscribe(
            {
                upcomingMoviesList.value = it
                upComingProgressBar.value = 0
            }, {
                Log.i("TAG", "fetchUpcomingMovies: $it")
            }
        )
    }

    fun fetchGenresSeries() {
        val genresObservable: Observable<Genres> = upcomingMoviesService.getGenresList()
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